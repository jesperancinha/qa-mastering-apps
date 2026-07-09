package org.jesperancinha.rockstarts.rockstarsmanager.data

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.*

data class ArtistDto(
    @param:JsonProperty("Id")
    val id: Long? = null,
    @param:JsonProperty("Name")
    val name: String? = null,
)