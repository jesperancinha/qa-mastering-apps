package org.jesperancinha.rockstarts.rockstarsmanager.repository

import org.jesperancinha.rockstarts.rockstarsmanager.model.Artist
import org.springframework.data.repository.CrudRepository

interface ArtistsRepository : CrudRepository<Artist, Long> {
    fun findArtistsByName(name: String): Artist
}