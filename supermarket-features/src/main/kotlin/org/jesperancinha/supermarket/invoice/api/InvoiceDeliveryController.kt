package org.jesperancinha.supermarket.invoice.api

import org.jesperancinha.supermarket.invoice.service.InvoiceProcessingService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class InvoiceController(
    private val invoiceProcessingService: InvoiceProcessingService
) {

    @PostMapping("/deliveries/invoice")
    fun sendInvoices(
        @Valid @RequestBody request: DeliveryInvoiceRequest
    ): ResponseEntity<List<DeliveryInvoiceResult>> {
        val results = invoiceProcessingService.sendInvoices(request.deliveryIds)
        return ResponseEntity.ok(results)
    }
}
