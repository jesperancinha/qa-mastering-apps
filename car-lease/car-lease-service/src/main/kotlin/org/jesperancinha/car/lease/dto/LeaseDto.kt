package org.jesperancinha.qa.lease.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class LeaseDto(
    @JsonProperty("id")
    val id: Long? = null,
    @JsonProperty("carId")
    val carId: Long? = null,
    @JsonProperty("customerId")
    val customerId: Long? = null,
    @JsonProperty("duration")
    val duration: Long,
    @JsonProperty("interestRate")
    val interestRate: Long,
    @JsonProperty("leaseRate")
    val leaseRate: Double? = null
)