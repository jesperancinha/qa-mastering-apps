package org.jesperancinha.car.lease.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class LeaseDto(
    @param:JsonProperty("id")
    val id: Long? = null,
    @param:JsonProperty("carId")
    val carId: Long? = null,
    @param:JsonProperty("customerId")
    val customerId: Long? = null,
    @param:JsonProperty("duration")
    val duration: Long,
    @param:JsonProperty("interestRate")
    val interestRate: Long,
    @param:JsonProperty("leaseRate")
    val leaseRate: Double? = null
)