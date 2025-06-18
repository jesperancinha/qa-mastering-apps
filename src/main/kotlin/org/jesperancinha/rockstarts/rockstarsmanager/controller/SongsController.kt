package org.jesperancinha.rockstarts.rockstarsmanager.controller

import org.jesperancinha.rockstarts.rockstarsmanager.data.SongDto
import org.jesperancinha.rockstarts.rockstarsmanager.services.SongsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/songs")
class SongsController(
    @Autowired
    private val songsService: SongsService,
) {

    @GetMapping("{id}")
    fun getSongById(
        @PathVariable id: Long,
    ): SongDto? {
        return songsService.getSongById(id)
    }

    @GetMapping("/filter/name/{songName}")
    fun getSongByName(
        @PathVariable songName: String,
    ): SongDto? {
        return songsService.getSongByName(songName)
    }

    @PostMapping
    fun saveSong(
        @RequestBody songDto: SongDto,
    ): SongDto? {
        return songsService.saveSong(songDto)
    }

    @PutMapping("/{id}")
    fun putArtist(
        @RequestBody songDto: SongDto,
        @PathVariable id: Long?,
    ): SongDto? {

        return songsService.updateSong(songDto.copy(id = id))
    }

    @DeleteMapping("{id}")
    fun deleteById(
        @PathVariable id: Long,
    ) {
        songsService.deleteById(id)
    }
}