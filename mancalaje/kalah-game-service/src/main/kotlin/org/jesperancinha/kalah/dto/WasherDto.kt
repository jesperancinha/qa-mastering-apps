package org.jesperancinha.kalah.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class WasherDto(
    @param:JsonProperty("id")
    val id: UUID?,
    @param:JsonProperty("playerDto")
    val playerDto: PlayerDto?,
)