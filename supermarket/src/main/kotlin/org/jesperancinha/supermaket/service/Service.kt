package org.jesperancinha.supermaket.service

import org.jesperancinha.supermaket.domain.Delivery
import org.jesperancinha.supermaket.domain.DeliveryStatus
import org.jesperancinha.supermaket.dto.*
import org.jesperancinha.supermaket.mapper.DeliveryMapper
import org.jesperancinha.supermaket.repository.DeliveryRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import java.time.Duration
import java.time.LocalDate
import java.time.ZoneId
import kotlin.math.roundToLong

@Service
class DeliveryService(
    private val deliveryRepository: DeliveryRepository,
    private val restTemplate: RestTemplate,
    @Value("\${invoice-service.url}")
    private val externalApiUrl: String
) {

    @Transactional
    fun createDelivery(request: DeliveryRequestDto): DeliveryResponseDto {
        when (request.status) {
            DeliveryStatus.IN_PROGRESS ->
                require(request.finishedAt == null) {
                    "finishedAt must be null for IN_PROGRESS deliveries"
                }

            DeliveryStatus.DELIVERED ->
                requireNotNull(request.finishedAt) {
                    "finishedAt must be provided for DELIVERED deliveries"
                }
        }

        val delivery = DeliveryMapper.fromDto(request)
        val saved = deliveryRepository.save<Delivery>(delivery)
        return DeliveryMapper.toDto(saved)
    }

    fun getInvoicesForDeliveries(request: InvoiceRequestDto) = request.deliveryIds
        .map { deliveryId ->
            SendInvoiceRequestDto(
                deliveryId = deliveryId,
                address = deliveryRepository
                    .findByIdOrNull(deliveryId)?.address ?: throw RuntimeException("Delivery $deliveryId not found!")
            ).let { sendInvoiceRequestDto ->
                restTemplate.postForObject(
                    "$externalApiUrl/v1/invoices",
                    sendInvoiceRequestDto,
                    SendInvoiceResponseDto::class.java
                ).let { sendInvoiceResponseDto ->
                    InvoiceResponseDto(
                        deliveryId = deliveryId,
                        invoiceId = sendInvoiceResponseDto?.id
                            ?: throw RuntimeException("No Invoice has been returned for $deliveryId")
                    )
                }
            }
        }

    fun getYesterdayDeliveriesSummary(): DeliveriesSummaryDto {
        val zoneId = ZoneId.of("Europe/Amsterdam")
        val today = LocalDate.now(zoneId)
        val yesterday = today.minusDays(1)
        val startOfYesterday = yesterday.atStartOfDay(zoneId).toInstant()
        val endOfYesterday = yesterday.plusDays(1).atStartOfDay(zoneId).toInstant()
        val deliveries =
            deliveryRepository.findByStartedAtBetweenOrderByStartedAtAsc(startOfYesterday, endOfYesterday)
        val count = deliveries.size
        val avgMinutesBetween: Long = deliveries
            .zipWithNext()
            .map { (a, b) ->
                Duration.between(a.startedAt, b.startedAt).toMinutes().toDouble() }
            .takeIf { it.isNotEmpty() }
            ?.average()
            ?.roundToLong()
            ?: 0L

        return DeliveriesSummaryDto(
            deliveries = count,
            averageMinutesBetweenDeliveryStart = avgMinutesBetween
        )
    }
}