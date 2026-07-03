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
        val startOfYesterday = yesterday.atStartOfDay(amsterdam).toInstant()
        val endOfYesterday = yesterday.plusDays(1).atStartOfDay(amsterdam).toInstant()

        val deliveriesStartedYesterday = deliveryService.getByStartedAtRange(startOfYesterday, endOfYesterday)

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
