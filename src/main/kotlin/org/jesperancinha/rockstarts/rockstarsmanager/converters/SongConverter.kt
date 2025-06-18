package org.jesperancinha.rockstarts.rockstarsmanager.converters

import org.jesperancinha.rockstarts.rockstarsmanager.data.SongDto
import org.jesperancinha.rockstarts.rockstarsmanager.model.Song

fun Song.toDto(): SongDto {
    return SongDto(
        id = id,
        name = name,
        year= year,
        artist= artist,
        shortName=shortName,
        bpm = bpm,
        duration=duration,
        genre= genre,
        spotifyId= spotifyId,
        album = album
    )
}

fun SongDto.toData(): Song {
    return Song(
        id = id,
        name = name,
        year= year,
        artist= artist,
        shortName=shortName,
        bpm = bpm,
        duration=duration,
        genre= genre,
        spotifyId= spotifyId,
        album = album
    )
}