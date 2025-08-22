package org.jesperancinha.supermarket.summary.api

data class BusinessSummaryResponse(
    val deliveries: Int,
    val averageMinutesBetweenDeliveryStart: Long
)
