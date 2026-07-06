package org.jesperancinha.car.lease.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CarDto(
    @param:JsonProperty("id")
    val id: Long? = null,
    @param:JsonProperty("make")
    val make: String? = null,
    @param:JsonProperty("model")
    val model: String? = null,
    @param:JsonProperty("version")
    val version: String? = null,
    @param:JsonProperty("numberDoors")
    val numberDoors: Long? = null,
    @param:JsonProperty("co2Emission")
    val co2Emission: Long? = null,
    @param:JsonProperty("grossPrice")
    val grossPrice: Long? = null,
    @param:JsonProperty("netPrice")
    val netPrice: Long,
    @param:JsonProperty("millage")
    val millage: Long
)