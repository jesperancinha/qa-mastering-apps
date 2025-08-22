package org.jesperancinha.supermarket.invoice.api

import org.jesperancinha.supermarket.invoice.service.InvoiceService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class InvoiceController(
    private val invoiceService: InvoiceService
) {

    @PostMapping("/deliveries/invoice")
    fun sendInvoices(
        @Valid @RequestBody request: DeliveryInvoiceRequest
    ): ResponseEntity<List<DeliveryInvoiceResult>> {
        val results = invoiceService.sendInvoices(request.deliveryIds)
        return ResponseEntity.ok(results)
    }
}
