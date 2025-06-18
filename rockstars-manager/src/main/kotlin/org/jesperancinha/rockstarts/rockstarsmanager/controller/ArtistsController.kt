package org.jesperancinha.rockstarts.rockstarsmanager.controller

import org.jesperancinha.rockstarts.rockstarsmanager.data.ArtistDto
import org.jesperancinha.rockstarts.rockstarsmanager.services.ArtistsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/artists")
class ArtistsController(private val artistsService: ArtistsService) {
    @GetMapping("{id}")
    fun getArtistsById(
        @PathVariable id: Long,
    ): ResponseEntity<ArtistDto?> {
        val artistsById = artistsService.getArtistsById(id)
        return if (Objects.isNull(artistsById)) {
            ResponseEntity.notFound()
                .build()
        } else ResponseEntity.ok(artistsById)
    }

    @GetMapping("/filter/name/{artistName}")
    fun getArtistByName(
        @PathVariable artistName: String,
    ): ResponseEntity<ArtistDto> {
        val artistsByName = artistsService.getArtistsByName(artistName)
        return if (Objects.isNull(artistsByName)) {
            ResponseEntity.notFound()
                .build()
        } else ResponseEntity.ok(artistsByName)
    }

    @PostMapping
    fun saveArtist(
        @RequestBody artistDto: ArtistDto,
    ) = artistsService.saveArtist(artistDto)

    @PutMapping("{id}")
    fun putArtist(
        @RequestBody artistDto: ArtistDto,
        @PathVariable id: Long?,
    ) = artistsService.updateArtist(artistDto.copy(id = id))

    @DeleteMapping("{id}")
    fun deleteById(
        @PathVariable id: Long,
    ) {
        artistsService.deleteById(id)
    }
}