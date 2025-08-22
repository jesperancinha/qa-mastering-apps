package org.jesperancinha.supermarket.invoice.service

import org.jesperancinha.supermarket.delivery.domain.DeliveryService
import org.jesperancinha.supermarket.invoice.api.DeliveryInvoiceResult
import org.jesperancinha.supermarket.invoice.client.InvoiceClient
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class InvoiceService(
    private val deliveryService: DeliveryService,
    private val invoiceClient: InvoiceClient
) {
    fun sendInvoices(deliveryIds: List<UUID>): List<DeliveryInvoiceResult> {
        val deliveries = deliveryService.getByIds(deliveryIds)
        return deliveries.map { d ->
            val resp = invoiceClient.sendInvoice(d.id, d.address)
            DeliveryInvoiceResult(deliveryId = d.id, invoiceId = resp.id)
        }
    }
}
