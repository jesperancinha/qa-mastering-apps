package org.jesperancinha.rockstarts.rockstarsmanager.services

import org.jesperancinha.rockstarts.rockstarsmanager.converters.toData
import org.jesperancinha.rockstarts.rockstarsmanager.converters.toDto
import org.jesperancinha.rockstarts.rockstarsmanager.data.ArtistDto
import org.jesperancinha.rockstarts.rockstarsmanager.data.SongDto
import org.jesperancinha.rockstarts.rockstarsmanager.repository.ArtistsRepository
import org.jesperancinha.rockstarts.rockstarsmanager.repository.SongsRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ArtistsService(private val artistsRepository: ArtistsRepository) {
    fun getArtistsById(id: Long) = artistsRepository.findByIdOrNull(id)?.toDto()

    fun deleteById(id: Long) = artistsRepository.deleteById(id)

    fun getArtistsByName(artistName: String) = artistsRepository.findArtistsByName(artistName).toDto()

    fun updateArtist(artistDto: ArtistDto) = artistsRepository.save(artistDto.toData()).toDto()

    fun saveArtist(artistDto: ArtistDto) = artistsRepository.save(artistDto.toData()).toDto()
}

@Service
class SongsService(private val songsRepository: SongsRepository) {
    fun getSongByName(songName: String): SongDto {
        return songsRepository.getSongsByName(songName).toDto()
    }

    fun getSongById(id: Long): SongDto? = songsRepository.findById(id)
        .let {
            if (it.isEmpty) null else it.get().toDto()
        }

    fun saveSong(songDto: SongDto) = songsRepository.save(songDto.toData()).toDto()

    fun updateSong(songDto: SongDto) = songsRepository.save(songDto.toData()).toDto()

    fun deleteById(id: Long) = songsRepository.deleteById(id)
}