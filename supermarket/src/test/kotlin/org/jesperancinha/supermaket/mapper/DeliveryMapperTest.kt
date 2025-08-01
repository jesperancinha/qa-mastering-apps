package org.jesperancinha.supermaket.mapper

import org.jesperancinha.supermaket.domain.Delivery
import org.jesperancinha.supermaket.domain.DeliveryStatus
import org.jesperancinha.supermaket.dto.DeliveryRequestDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.time.Instant
import java.util.*

class DeliveryMapperTest {

    @Test
    fun `should map from DTO to entity`() {
        // Given
        val now = Instant.now()
        val dto = DeliveryRequestDto(
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = now,
            finishedAt = null,
            status = DeliveryStatus.IN_PROGRESS
        )

        // When
        val entity = DeliveryMapper.fromDto(dto)

        // Then
        assertNull(entity.id)
        assertEquals(dto.vehicleId, entity.vehicleId)
        assertEquals(dto.address, entity.address)
        assertEquals(dto.startedAt, entity.startedAt)
        assertEquals(dto.finishedAt, entity.finishedAt)
        assertEquals(dto.status, entity.status)
    }

    @Test
    fun `should map from entity to DTO`() {
        // Given
        val id = UUID.randomUUID()
        val now = Instant.now()
        val entity = Delivery(
            id = id,
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = now,
            finishedAt = now.plusSeconds(3600),
            status = DeliveryStatus.DELIVERED
        )

        // When
        val dto = DeliveryMapper.toDto(entity)

        // Then
        assertEquals(entity.id, dto.id)
        assertEquals(entity.vehicleId, dto.vehicleId)
        assertEquals(entity.address, dto.address)
        assertEquals(entity.startedAt, dto.startedAt)
        assertEquals(entity.finishedAt, dto.finishedAt)
        assertEquals(entity.status, dto.status)
    }

    @Test
    fun `should handle null values when mapping from entity to DTO`() {
        // Given
        val entity = Delivery(
            id = null,
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = Instant.now(),
            finishedAt = null,
            status = DeliveryStatus.IN_PROGRESS
        )

        // When
        val dto = DeliveryMapper.toDto(entity)

        // Then
        assertNull(dto.id)
        assertEquals(entity.vehicleId, dto.vehicleId)
        assertEquals(entity.address, dto.address)
        assertEquals(entity.startedAt, dto.startedAt)
        assertNull(dto.finishedAt)
        assertEquals(entity.status, dto.status)
    }
}