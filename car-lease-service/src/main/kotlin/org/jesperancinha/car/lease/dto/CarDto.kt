package org.jesperancinha.car.lease.dto

data class CarDto(
    val id: Long? = null,
    val make: String? = null,
    val model: String? = null,
    val version: String? = null,
    val numberDoors: Long? = null,
    val co2Emission: Long? = null,
    val grossPrice: Long? = null,
    val netPrice: Long,
    val millage: Long
)