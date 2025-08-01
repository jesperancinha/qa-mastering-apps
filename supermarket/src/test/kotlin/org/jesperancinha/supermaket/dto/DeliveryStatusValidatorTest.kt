package org.jesperancinha.supermaket.dto

import io.mockk.every
import io.mockk.mockk
import jakarta.validation.ConstraintValidatorContext
import org.jesperancinha.supermaket.domain.DeliveryStatus
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.Instant

class DeliveryStatusValidatorTest {

    private val validator = DeliveryStatusValidator()
    private val context = mockk<ConstraintValidatorContext>()

    @Test
    fun `should validate IN_PROGRESS delivery with null finishedAt as valid`() {
        // Given
        val dto = DeliveryRequestDto(
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = Instant.now(),
            finishedAt = null,
            status = DeliveryStatus.IN_PROGRESS
        )

        // When
        val result = validator.isValid(dto, context)

        // Then
        assertTrue(result)
    }

    @Test
    fun `should validate IN_PROGRESS delivery with non-null finishedAt as invalid`() {
        // Given
        val dto = DeliveryRequestDto(
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = Instant.now(),
            finishedAt = Instant.now(),
            status = DeliveryStatus.IN_PROGRESS
        )

        // When
        val result = validator.isValid(dto, context)

        // Then
        assertFalse(result)
    }

    @Test
    fun `should validate DELIVERED delivery with non-null finishedAt as valid`() {
        // Given
        val dto = DeliveryRequestDto(
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = Instant.now(),
            finishedAt = Instant.now(),
            status = DeliveryStatus.DELIVERED
        )

        // When
        val result = validator.isValid(dto, context)

        // Then
        assertTrue(result)
    }

    @Test
    fun `should validate DELIVERED delivery with null finishedAt as invalid`() {
        // Given
        val dto = DeliveryRequestDto(
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = Instant.now(),
            finishedAt = null,
            status = DeliveryStatus.DELIVERED
        )

        // When
        val result = validator.isValid(dto, context)

        // Then
        assertFalse(result)
    }
}

/**
 * Test for the DeliveryStatusConstraint annotation using a test bean
 */
class DeliveryStatusConstraintTest {

    @Test
    fun `should test constraint annotation with valid bean`() {
        // Given
        val dto = DeliveryRequestDto(
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = Instant.now(),
            finishedAt = null,
            status = DeliveryStatus.IN_PROGRESS
        )

        // When/Then
        val validator = jakarta.validation.Validation.buildDefaultValidatorFactory().validator
        val violations = validator.validate(dto)
        assertTrue(violations.isEmpty())
    }

    @Test
    fun `should test constraint annotation with invalid bean`() {
        // Given
        val dto = DeliveryRequestDto(
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = Instant.now(),
            finishedAt = null,
            status = DeliveryStatus.DELIVERED // Invalid: DELIVERED requires non-null finishedAt
        )

        // When/Then
        val validator = jakarta.validation.Validation.buildDefaultValidatorFactory().validator
        val violations = validator.validate(dto)
        assertFalse(violations.isEmpty())
        assertTrue(violations.any { it.message == "Invalid delivery status and finishedAt combination" })
    }
}