package org.jesperancinha.supermarket.delivery.api

import org.jesperancinha.supermarket.delivery.domain.Delivery
import org.jesperancinha.supermarket.delivery.domain.DeliveryStatus
import org.jesperancinha.supermarket.delivery.domain.DeliveryStatus.*
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.Instant
import java.util.UUID

data class DeliveryCreateRequest(
    @field:NotBlank
    @field:Size(max = 64)
    val vehicleId: String,

    @field:NotBlank
    @field:Size(max = 255)
    val address: String,

    @field:NotNull
    val startedAt: Instant,

    val finishedAt: Instant? = null,

    @field:NotNull
    val status: DeliveryStatus
) {
    @AssertTrue(message = "finishedAt must be null when status is IN_PROGRESS; provided when status is DELIVERED")
    fun isFinishedAtValid(): Boolean =
        when (status) {
            IN_PROGRESS -> finishedAt == null
            DELIVERED -> finishedAt != null
        }
}

data class DeliveryResponse(
    val id: UUID,
    val vehicleId: String,
    val address: String,
    val startedAt: Instant,
    val finishedAt: Instant?,
    val status: DeliveryStatus
) {
    companion object {
        fun fromDomain(d: Delivery) = DeliveryResponse(
            id = d.id,
            vehicleId = d.vehicleId,
            address = d.address,
            startedAt = d.startedAt,
            finishedAt = d.finishedAt,
            status = d.status
        )
    }
}
