package org.jesperancinha.car.lease.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CustomerDto(
    @param:JsonProperty("id")
    val id: Long? = null,
    @param:JsonProperty("name")
    val name: String? = null,
    @param:JsonProperty("street")
    val street: String? = null,
    @param:JsonProperty("houseNumber")
    val houseNumber: Long? = null,
    @param:JsonProperty("zipCode")
    val zipCode: String? = null,
    @param:JsonProperty("place")
    val place: String? = null,
    @param:JsonProperty("email")
    val email: String? = null,
    @param:JsonProperty("phoneNumber")
    val phoneNumber: String? = null
)