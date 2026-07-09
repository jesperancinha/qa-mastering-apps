package org.jesperancinha.supermaket.exception

import java.util.UUID

class DeliveryNotFoundException(uuid: UUID) : RuntimeException("Delivery not found for UUID: $uuid")

class InvoiceNotReturnedException(uuid: UUID) : RuntimeException("Invoice not returned for UUID: $uuid")