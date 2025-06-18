package org.jesperancinha.supermaket.controller

import org.jesperancinha.supermaket.dto.DeliveriesSummaryDto
import org.jesperancinha.supermaket.dto.DeliveryRequestDto
import org.jesperancinha.supermaket.dto.InvoiceRequestDto
import org.jesperancinha.supermaket.service.DeliveryService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/deliveries")
class DeliveryController(
    private val service: DeliveryService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createDelivery(@RequestBody request: DeliveryRequestDto) = service.createDelivery(request)

    @PostMapping("/invoice")
    fun getInvoices(@RequestBody request: InvoiceRequestDto) = service.getInvoicesForDeliveries(request)

    @GetMapping("/business-summary")
    fun getBusinessSummary(): DeliveriesSummaryDto = service.getYesterdayDeliveriesSummary()
}