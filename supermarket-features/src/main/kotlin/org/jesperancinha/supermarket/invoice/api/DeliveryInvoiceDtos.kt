package org.jesperancinha.supermarket.invoice.api

import jakarta.validation.constraints.NotEmpty
import java.util.UUID

data class DeliveryInvoiceRequest(
    @field:NotEmpty(message = "deliveryIds must not be empty")
    val deliveryIds: List<UUID>
)

data class DeliveryInvoiceResult(
    val deliveryId: UUID,
    val invoiceId: UUID
)
