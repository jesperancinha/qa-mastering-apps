package org.jesperancinha.supermarket.delivery.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "deliveries")
data class Delivery(
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    val id: UUID,
    val vehicleId: String,
    val address: String,
    val startedAt: Instant,
    val finishedAt: Instant?,
    @Enumerated(EnumType.STRING)
    val status: DeliveryStatus
)
