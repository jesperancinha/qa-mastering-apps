package org.jesperancinha.qa.lease.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CarDto(
    @JsonProperty("id")
    val id: Long? = null,
    @JsonProperty("make")
    val make: String? = null,
    @JsonProperty("model")
    val model: String? = null,
    @JsonProperty("version")
    val version: String? = null,
    @JsonProperty("numberDoors")
    val numberDoors: Long? = null,
    @JsonProperty("co2Emission")
    val co2Emission: Long? = null,
    @JsonProperty("grossPrice")
    val grossPrice: Long? = null,
    @JsonProperty("netPrice")
    val netPrice: Long,
    @JsonProperty("millage")
    val millage: Long
)