package org.jesperancinha.supermarket.summary.api

import org.jesperancinha.supermarket.summary.service.BusinessSummaryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SummaryController(
    private val summaryService: BusinessSummaryService
) {

    @GetMapping("/deliveries/business-summary")
    fun businessSummary(): ResponseEntity<BusinessSummaryResponse> =
        ResponseEntity.ok(summaryService.yesterdaySummary())
}
