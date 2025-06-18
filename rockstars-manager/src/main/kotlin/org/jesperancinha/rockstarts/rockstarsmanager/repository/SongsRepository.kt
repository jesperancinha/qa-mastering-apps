package org.jesperancinha.rockstarts.rockstarsmanager.repository

import org.jesperancinha.rockstarts.rockstarsmanager.model.Song
import org.springframework.data.repository.CrudRepository

interface SongsRepository : CrudRepository<Song, Long?> {
    fun getSongsByName(name: String): Song
}