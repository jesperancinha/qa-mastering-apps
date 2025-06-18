package org.jesperancinha.supermaket.dto

import org.jesperancinha.supermaket.domain.DeliveryStatus
import java.time.Instant
import java.util.*

data class DeliveryRequestDto(
    val vehicleId: String,
    val address: String,
    val startedAt: Instant,
    val finishedAt: Instant?,
    val status: DeliveryStatus
)


data class DeliveryResponseDto(
    val id: UUID? = null,
    val vehicleId: String,
    val address: String,
    val startedAt: Instant,
    val finishedAt: Instant? = null,
    val status: DeliveryStatus
)

data class InvoiceRequestDto(
    val deliveryIds: List<UUID>
)

data class InvoiceResponseDto(
    val deliveryId: UUID,
    val invoiceId: UUID
)

data class SendInvoiceRequestDto(
    val deliveryId: UUID,
    val address: String,
)

data class SendInvoiceResponseDto(
    val id: UUID,
    val sent: Boolean,
)

data class DeliveriesSummaryDto(
    val deliveries: Int,
    val averageMinutesBetweenDeliveryStart: Long
)
