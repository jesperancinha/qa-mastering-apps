package org.jesperancinha.rockstarts.rockstarsmanager.converters

import org.jesperancinha.rockstarts.rockstarsmanager.data.ArtistDto
import org.jesperancinha.rockstarts.rockstarsmanager.model.Artist

fun Artist.toDto(): ArtistDto {
    return ArtistDto(
        id = id,
        name = name

    )
}

fun ArtistDto.toData(): Artist {
    return Artist(
        id = id,
        name = name
    )
}