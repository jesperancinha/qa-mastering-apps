package org.jesperancinha.kalah.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class BoardDto(
    @param:JsonProperty("id")
    val id: UUID?,
    @param:JsonProperty("pitDtos")
    val pitDtos: List<WasherDto?>,
    @param:JsonProperty("pitDtoOne")
    val washerDtoOne: WasherDto?,
    @param:JsonProperty("kalahOne")
    val kalahOne: WasherDto?,
    @param:JsonProperty("playerDtoOne")
    val playerDtoOne: PlayerDto?,
    @param:JsonProperty("pitDtoTwo")
    val pitDtoTwo: WasherDto?,
    @param:JsonProperty("kalahTwo")
    val kalahTwo: WasherDto?,
    @param:JsonProperty("playerDtoTwo")
    val playerDtoTwo: PlayerDto?,
    @param:JsonProperty("currentPlayerDto")
    val currentPlayerDto: PlayerDto?,
    @param:JsonProperty("winnerDto")
    val winnerDto: PlayerDto?,
)