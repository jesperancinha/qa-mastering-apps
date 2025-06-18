package org.jesperancinha.supermaket.dto

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import jakarta.validation.constraints.PastOrPresent
import org.jesperancinha.supermaket.domain.DeliveryStatus
import java.time.Instant
import java.util.*
import kotlin.reflect.KClass

// For a small project there is NO inconvenience in using DTOS in one single file.
// If anyone says otherwise for a small project like this one, then please think of how that would look like.
// It would look like Java code, but in Kotlin, which isn't what is intended with Kotlin files.
// This is perfect and the way to declare Dtos in projects, if there are that many Dtos's in a project then they can be declared in separate files, where the concern makes sense.

@DeliveryStatusConstraint
data class DeliveryRequestDto(
    val vehicleId: String,
    val address: String,
    @field:PastOrPresent
    val startedAt: Instant,
    val finishedAt: Instant?,
    val status: DeliveryStatus
)


data class DeliveryResponseDto(
    val id: UUID? = null,
    val vehicleId: String,
    val address: String,
    val startedAt: Instant,
    val finishedAt: Instant? = null,
    val status: DeliveryStatus
)

data class InvoiceRequestDto(
    val deliveryIds: List<UUID>
)

data class InvoiceResponseDto(
    val deliveryId: UUID,
    val invoiceId: UUID
)

data class SendInvoiceRequestDto(
    val deliveryId: UUID,
    val address: String,
)

data class SendInvoiceResponseDto(
    val id: UUID,
    val sent: Boolean,
)

data class DeliveriesSummaryDto(
    val deliveries: Int,
    val averageMinutesBetweenDeliveryStart: Long
)

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DeliveryStatusValidator::class])
annotation class DeliveryStatusConstraint(
    val message: String = "Invalid delivery status and finishedAt combination",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class DeliveryStatusValidator : ConstraintValidator<DeliveryStatusConstraint, DeliveryRequestDto> {
    override fun isValid(dto: DeliveryRequestDto, context: ConstraintValidatorContext): Boolean {
        return when (dto.status) {
            DeliveryStatus.IN_PROGRESS -> dto.finishedAt == null
            DeliveryStatus.DELIVERED -> dto.finishedAt != null
        }
    }
}