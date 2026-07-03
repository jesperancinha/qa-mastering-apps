package org.jesperancinha.supermarket.invoice.service

import org.jesperancinha.supermarket.delivery.domain.DeliveryService
import org.jesperancinha.supermarket.invoice.api.DeliveryInvoiceResult
import org.jesperancinha.supermarket.invoice.client.InvoiceClient
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class InvoiceProcessingService(
    private val deliveryService: DeliveryService,
    private val invoiceClient: InvoiceClient
) {
    fun sendInvoices(deliveryIds: List<UUID>): List<DeliveryInvoiceResult> {
        val deliveries = deliveryService.getByIds(deliveryIds)

        val foundIds = deliveries.mapNotNull { it.id }.toSet()
        val missingIds = deliveryIds.toSet() - foundIds
        if (missingIds.isNotEmpty()) {
            throw NoSuchElementException("Delivery id(s) not found: ${missingIds.joinToString(", ")}")
        }

        return deliveries.map { d ->
            val deliveryId = checkNotNull(d.id) { "Persisted delivery must have an id" }
            val resp = invoiceClient.sendInvoice(deliveryId, d.address)
            DeliveryInvoiceResult(deliveryId = deliveryId, invoiceId = resp.id)
        }
    }
}
