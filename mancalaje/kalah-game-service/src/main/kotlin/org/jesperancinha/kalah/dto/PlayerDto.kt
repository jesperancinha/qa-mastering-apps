package org.jesperancinha.kalah.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class PlayerDto(
    @param:JsonProperty("id")
    val id: UUID?,
    @param:JsonProperty("username")
    val username: String?,
)