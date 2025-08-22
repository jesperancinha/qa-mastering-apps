package org.jesperancinha.supermarket.summary.service

import org.jesperancinha.supermarket.delivery.domain.DeliveryService
import org.jesperancinha.supermarket.summary.api.BusinessSummaryResponse
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDate
import java.time.ZoneId
import kotlin.math.roundToLong

@Service
class BusinessSummaryService(
    private val deliveryService: DeliveryService
) {
    private val amsterdam: ZoneId = ZoneId.of("Europe/Amsterdam")

    fun yesterdaySummary(): BusinessSummaryResponse {
        val yesterday = LocalDate.now(amsterdam).minusDays(1)

        val deliveriesStartedYesterday = deliveryService.getAll().filter { d ->
            val zdt = d.startedAt.atZone(amsterdam)
            zdt.toLocalDate().isEqual(yesterday)
        }.sortedBy { it.startedAt }

        val count = deliveriesStartedYesterday.size
        val avgMinutes = deliveriesStartedYesterday
            .zipWithNext { a, b ->
                Duration
                    .between(
                        a.startedAt, b.startedAt
                    )
                    .toMinutes()
            }
            .takeIf { it.isNotEmpty() }
            ?.average()
            ?.roundToLong()
            ?: 0L

        return BusinessSummaryResponse(deliveries = count, averageMinutesBetweenDeliveryStart = avgMinutes)
    }
}
