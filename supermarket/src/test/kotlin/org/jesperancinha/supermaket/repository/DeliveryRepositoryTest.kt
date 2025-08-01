package org.jesperancinha.supermaket.repository

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.jesperancinha.supermaket.domain.Delivery
import org.jesperancinha.supermaket.domain.DeliveryStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

class DeliveryRepositoryTest {

    @Test
    fun `should find deliveries between dates`() {
        // Given
        val mockRepository = mockk<DeliveryRepository>()
        val now = Instant.now()
        val yesterday = now.minus(1, ChronoUnit.DAYS)
        val tomorrow = now.plus(1, ChronoUnit.DAYS)
        
        val delivery1 = Delivery(
            id = UUID.randomUUID(),
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = now,
            status = DeliveryStatus.IN_PROGRESS
        )
        
        val delivery2 = Delivery(
            id = UUID.randomUUID(),
            vehicleId = "vehicle2",
            address = "456 Oak Ave",
            startedAt = now.plus(2, ChronoUnit.HOURS),
            status = DeliveryStatus.IN_PROGRESS
        )
        
        val expectedDeliveries = listOf(delivery1, delivery2)
        
        // When
        every { 
            mockRepository.findByStartedAtBetweenOrderByStartedAtAsc(yesterday, tomorrow) 
        } returns expectedDeliveries
        
        val actualDeliveries = mockRepository.findByStartedAtBetweenOrderByStartedAtAsc(yesterday, tomorrow)
        
        // Then
        assertEquals(expectedDeliveries, actualDeliveries)
        verify(exactly = 1) { 
            mockRepository.findByStartedAtBetweenOrderByStartedAtAsc(yesterday, tomorrow) 
        }
    }
    
    @Test
    fun `should return empty list when no deliveries found in date range`() {
        // Given
        val mockRepository = mockk<DeliveryRepository>()
        val start = Instant.parse("2025-01-01T00:00:00Z")
        val end = Instant.parse("2025-01-02T00:00:00Z")
        
        // When
        every { 
            mockRepository.findByStartedAtBetweenOrderByStartedAtAsc(start, end) 
        } returns emptyList()
        
        val actualDeliveries = mockRepository.findByStartedAtBetweenOrderByStartedAtAsc(start, end)
        
        // Then
        assertEquals(emptyList<Delivery>(), actualDeliveries)
        verify(exactly = 1) { 
            mockRepository.findByStartedAtBetweenOrderByStartedAtAsc(start, end) 
        }
    }
    
    @Test
    fun `should save delivery`() {
        // Given
        val mockRepository = mockk<DeliveryRepository>()
        val delivery = Delivery(
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = Instant.now(),
            status = DeliveryStatus.IN_PROGRESS
        )
        
        val savedDelivery = delivery.copy(id = UUID.randomUUID())
        
        // When
        every { mockRepository.save(delivery) } returns savedDelivery
        
        val result = mockRepository.save(delivery)
        
        // Then
        assertEquals(savedDelivery, result)
        verify(exactly = 1) { mockRepository.save(delivery) }
    }
    
    @Test
    fun `should find delivery by id`() {
        // Given
        val mockRepository = mockk<DeliveryRepository>()
        val id = UUID.randomUUID()
        val delivery = Delivery(
            id = id,
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = Instant.now(),
            status = DeliveryStatus.IN_PROGRESS
        )
        
        // When
        every { mockRepository.findById(id) } returns Optional.of(delivery)
        
        val result = mockRepository.findById(id)
        
        // Then
        assertEquals(Optional.of(delivery), result)
        verify(exactly = 1) { mockRepository.findById(id) }
    }
    
    @Test
    fun `should return empty optional when delivery not found`() {
        // Given
        val mockRepository = mockk<DeliveryRepository>()
        val id = UUID.randomUUID()
        
        // When
        every { mockRepository.findById(id) } returns Optional.empty()
        
        val result = mockRepository.findById(id)
        
        // Then
        assertEquals(Optional.empty<Delivery>(), result)
        verify(exactly = 1) { mockRepository.findById(id) }
    }
}