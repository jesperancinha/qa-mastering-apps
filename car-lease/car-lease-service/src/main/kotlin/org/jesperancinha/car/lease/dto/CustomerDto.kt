package org.jesperancinha.qa.lease.dto

import com.fasterxml.jackson.annotation.JsonProperty

class CustomerDto(
    @JsonProperty("id")
    val id: Long? = null,
    @JsonProperty("name")
    val name: String? = null,
    @JsonProperty("street")
    val street: String? = null,
    @JsonProperty("houseNumber")
    val houseNumber: Long? = null,
    @JsonProperty("zipCode")
    val zipCode: String? = null,
    @JsonProperty("place")
    val place: String? = null,
    @JsonProperty("email")
    val email: String? = null,
    @JsonProperty("phoneNumber")
    val phoneNumber: String? = null
)