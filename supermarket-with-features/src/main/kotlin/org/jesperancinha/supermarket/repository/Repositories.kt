package org.jesperancinha.supermarket.repository

import org.jesperancinha.supermarket.delivery.domain.Delivery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.Instant
import java.util.*

interface DeliveryRepository : JpaRepository<Delivery, UUID> {
    @Query("""
        SELECT d 
        FROM Delivery d 
        WHERE d.startedAt >= :start AND d.startedAt < :end
        ORDER BY d.startedAt ASC
    """)
    fun findByStartedAtBetweenOrderByStartedAtAsc(
        @Param("start") start: Instant,
        @Param("end") end: Instant
    ): List<Delivery>
}
