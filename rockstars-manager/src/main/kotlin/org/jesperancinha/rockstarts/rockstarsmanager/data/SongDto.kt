package org.jesperancinha.rockstarts.rockstarsmanager.data

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Id

data class SongDto(
    @param:JsonProperty("Id")
    val id: Long? = null,

    @param:JsonProperty("Name")
    val name: String? = null,

    @param:JsonProperty("Year")
    val year: Int? = null,

    @param:JsonProperty("Artist")
    val artist: String? = null,

    @param:JsonProperty("Shortname")
    val shortName: String? = null,

    @param:JsonProperty("Bpm")
    val bpm: Long? = null,

    @param:JsonProperty("Duration")
    val duration: Long? = null,

    @param:JsonProperty("Genre")
    val genre: String? = null,

    @param:JsonProperty("SpotifyId")
    val spotifyId: String? = null,

    @param:JsonProperty("Album")
    val album: String? = null,
)