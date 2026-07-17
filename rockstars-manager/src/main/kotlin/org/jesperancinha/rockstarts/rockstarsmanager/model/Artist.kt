package org.jesperancinha.rockstarts.rockstarsmanager.model

import jakarta.persistence.*

@Entity
@Table(name = "artists")
data class Artist(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val name: String? = null,
)