package org.jesperancinha.car.lease.dto

data class LeaseDto(
    val id: Long? = null,
    val carId: Long? = null,
    val customerId: Long? = null,
    val duration: Long,
    val interestRate: Long,
    val leaseRate: Double? = null
)