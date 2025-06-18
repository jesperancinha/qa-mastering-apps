package org.jesperancinha.supermaket.domain

import jakarta.persistence.*
import java.time.Instant
import java.util.*

enum class DeliveryStatus {
    IN_PROGRESS, DELIVERED
}

@Entity
@Table(name = "deliveries")
data class Delivery(
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    val id: UUID? = null,

    val vehicleId: String,

    val address: String,

    val startedAt: Instant,

    val finishedAt: Instant? = null,

    @Enumerated(EnumType.STRING)
    val status: DeliveryStatus
)