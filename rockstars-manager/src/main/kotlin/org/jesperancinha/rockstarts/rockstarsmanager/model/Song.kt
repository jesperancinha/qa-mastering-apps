package org.jesperancinha.rockstarts.rockstarsmanager.model

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Entity
@Table(name = "songs")
class Song(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val name: String? = null,
    val year: Int? = null,
    val artist: String? = null,
    val shortName: String? = null,
    val bpm: Long? = null,
    val duration: Long? = null,
    val genre: String? = null,
    val spotifyId: String? = null,
    val album: String? = null
)