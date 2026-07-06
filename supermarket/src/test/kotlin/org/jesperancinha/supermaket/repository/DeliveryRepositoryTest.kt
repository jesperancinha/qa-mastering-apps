package org.jesperancinha.supermaket.repository

import org.jesperancinha.supermaket.domain.Delivery
import org.jesperancinha.supermaket.domain.DeliveryStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.jesperancinha.supermaket.TestcontainersConfiguration
import org.springframework.context.annotation.Import
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestcontainersConfiguration::class)
class DeliveryRepositoryTest @Autowired constructor(
    private val deliveryRepository: DeliveryRepository
) {

    @Test
    fun `should find deliveries between dates ordered by startedAt`() {
        val now = Instant.now()
        val yesterday = now.minus(1, ChronoUnit.DAYS)
        val tomorrow = now.plus(1, ChronoUnit.DAYS)

        val delivery1 = deliveryRepository.save(
            Delivery(
                vehicleId = "vehicle1",
                address = "123 Main St",
                startedAt = now,
                status = DeliveryStatus.IN_PROGRESS
            )
        )
        val delivery2 = deliveryRepository.save(
            Delivery(
                vehicleId = "vehicle2",
                address = "456 Oak Ave",
                startedAt = now.plus(2, ChronoUnit.HOURS),
                status = DeliveryStatus.IN_PROGRESS
            )
        )

        val result = deliveryRepository.findByStartedAtBetweenOrderByStartedAtAsc(yesterday, tomorrow)

        assertEquals(listOf(delivery1.id, delivery2.id), result.map { it.id })
    }

    @Test
    fun `should return empty list when no deliveries found in date range`() {
        val start = Instant.parse("2025-01-01T00:00:00Z")
        val end = Instant.parse("2025-01-02T00:00:00Z")

        val result = deliveryRepository.findByStartedAtBetweenOrderByStartedAtAsc(start, end)

        assertTrue(result.isEmpty())
    }

    @Test
    fun `should exclude deliveries started outside the given range`() {
        val now = Instant.now()
        deliveryRepository.save(
            Delivery(
                vehicleId = "vehicle1",
                address = "123 Main St",
                startedAt = now.minus(5, ChronoUnit.DAYS),
                status = DeliveryStatus.IN_PROGRESS
            )
        )

        val result = deliveryRepository.findByStartedAtBetweenOrderByStartedAtAsc(
            now.minus(1, ChronoUnit.DAYS),
            now.plus(1, ChronoUnit.DAYS)
        )

        assertTrue(result.isEmpty())
    }

    @Test
    fun `should persist and load a delivery by id`() {
        val saved = deliveryRepository.save(
            Delivery(
                vehicleId = "vehicle1",
                address = "123 Main St",
                startedAt = Instant.now(),
                status = DeliveryStatus.IN_PROGRESS
            )
        )

        val found = deliveryRepository.findById(requireNotNull(saved.id))

        assertTrue(found.isPresent)
        assertEquals(saved.vehicleId, found.get().vehicleId)
    }

    @Test
    fun `should return empty optional when delivery not found`() {
        val result = deliveryRepository.findById(UUID.randomUUID())

        assertTrue(result.isEmpty)
    }
}
