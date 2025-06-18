package org.jesperancinha.rockstarts.rockstarsmanager.data

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.*

data class ArtistDto(
    @JsonProperty("Id")
    val id: Long? = null,
    @JsonProperty("Name")
    val name: String? = null,
)