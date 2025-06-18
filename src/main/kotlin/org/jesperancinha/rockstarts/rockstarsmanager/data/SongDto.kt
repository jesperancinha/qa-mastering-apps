package org.jesperancinha.rockstarts.rockstarsmanager.data

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

data class SongDto(
    @JsonProperty("Id")
    val id: Long? = null,

    @JsonProperty("Name")
    val name: String? = null,

    @JsonProperty("Year")
    val year: Int? = null,

    @JsonProperty("Artist")
    val artist: String? = null,

    @JsonProperty("Shortname")
    val shortName: String? = null,

    @JsonProperty("Bpm")
    val bpm: Long? = null,

    @JsonProperty("Duration")
    val duration: Long? = null,

    @JsonProperty("Genre")
    val genre: String? = null,

    @JsonProperty("SpotifyId")
    val spotifyId: String? = null,

    @JsonProperty("Album")
    val album: String? = null,
)