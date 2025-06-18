package org.jesperancinha.supermaket.controller

import jakarta.validation.Valid
import org.jesperancinha.supermaket.dto.DeliveriesSummaryDto
import org.jesperancinha.supermaket.dto.DeliveryRequestDto
import org.jesperancinha.supermaket.dto.InvoiceRequestDto
import org.jesperancinha.supermaket.service.DeliveryService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * The purpose of the controller is to receive Dtos and use a minimum amount of logic.
 * The business layer, with services bridges this gap, and it is unwise to use, mappers, in the controller layer.
 * The Controller layer shouldn't be aware at all the domain model, because that it is not its concern.
 * In addition to that, making integration tests with a controller that contains domain data types is cumbersome and leads to bad results.
 */

@Validated
@RestController
@RequestMapping("/deliveries")
class DeliveryController(
    private val service: DeliveryService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createDelivery(@RequestBody @Valid request: DeliveryRequestDto) = service.createDelivery(request)

    @PostMapping("/invoice")
    fun getInvoices(@RequestBody request: InvoiceRequestDto) = service.getInvoicesForDeliveries(request)

    @GetMapping("/business-summary")
    fun getBusinessSummary(): DeliveriesSummaryDto = service.getYesterdayDeliveriesSummary()
}