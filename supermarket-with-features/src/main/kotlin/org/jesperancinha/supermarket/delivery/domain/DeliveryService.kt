package org.jesperancinha.supermarket.delivery.domain

import org.jesperancinha.supermarket.repository.DeliveryRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Service
class DeliveryService(
    private val deliveryRepository: DeliveryRepository
) {

    fun createDelivery(
        vehicleId: String,
        address: String,
        startedAt: Instant,
        finishedAt: Instant?,
        status: DeliveryStatus
    ): Delivery {
        if (status == DeliveryStatus.IN_PROGRESS && finishedAt != null) {
            throw IllegalArgumentException("finishedAt must be null when status is IN_PROGRESS")
        }
        if (status == DeliveryStatus.DELIVERED && finishedAt == null) {
            throw IllegalArgumentException("finishedAt must be provided when status is DELIVERED")
        }

        val delivery = Delivery(
            id = UUID.randomUUID(),
            vehicleId = vehicleId,
            address = address,
            startedAt = startedAt,
            finishedAt = finishedAt,
            status = status
        )

        return deliveryRepository.save(delivery)
    }

    fun getById(id: UUID): Delivery =
        deliveryRepository.findByIdOrNull(id) ?: throw NoSuchElementException("Delivery not found: $id")

    fun getByIds(ids: Collection<UUID>): List<Delivery> =
        deliveryRepository.findAllById(ids).toList()

    fun getAll(): List<Delivery> = deliveryRepository.findAll()
}
