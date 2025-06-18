package org.jesperancinha.rockstarts.rockstarsmanager

import com.fasterxml.jackson.databind.ObjectMapper
import lombok.extern.slf4j.Slf4j
import org.apache.commons.io.IOUtils
import org.jesperancinha.rockstarts.rockstarsmanager.converters.toData
import org.jesperancinha.rockstarts.rockstarsmanager.data.ArtistDto
import org.jesperancinha.rockstarts.rockstarsmanager.data.SongDto
import org.jesperancinha.rockstarts.rockstarsmanager.repository.ArtistsRepository
import org.jesperancinha.rockstarts.rockstarsmanager.repository.SongsRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.http.*
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.io.IOException
import java.nio.charset.Charset
import java.util.*
import java.util.function.Predicate

@Component
@Slf4j
@Profile("local", "prod")
class RockstarsInitializer(
    private val songsRepository: SongsRepository,
    private val artistsRepository: ArtistsRepository
) : CommandLineRunner {
    private val objectMapper = ObjectMapper()

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        val allArtists = allArtists
        val allSongs = allSongs
        Optional.of(allArtists)
            .flatMap { response: ResponseEntity<Array<ArtistDto>> -> Optional.ofNullable(response.body) }
            .ifPresent { artists: Array<ArtistDto>? ->
                Arrays.stream(artists)
                    .forEach { artistDto: ArtistDto -> artistsRepository.save(artistDto.toData()) }
            }
        Optional.of(allSongs)
            .flatMap { response: ResponseEntity<Array<SongDto>> -> Optional.ofNullable(response.body) }
            .ifPresent { songs: Array<SongDto>? ->
                Arrays.stream(songs)
                    .filter(SONG_DTO_PREDICATE)
                    .forEach { songDto: SongDto -> songsRepository.save(songDto.toData()) }
            }
    }

    @get:Throws(IOException::class)
    private val allArtists: ResponseEntity<Array<ArtistDto>>
        get() = try {
            fetchResponseEntity(
                HTTPS_WWW_TEAMROCKSTARS_NL_SITES_DEFAULT_FILES_ARTISTS_JSON,
                Array<ArtistDto>::class.java
            )
        } catch (e: Exception) {
            ResponseEntity.of(
                Optional.of(
                    objectMapper.readValue(
                        IOUtils.toString(javaClass.getResourceAsStream("/artists.json"), Charset.defaultCharset()),
                        Array<ArtistDto>::class.java
                    )
                )
            )
        }

    @get:Throws(IOException::class)
    private val allSongs: ResponseEntity<Array<SongDto>>
        get() = try {
            fetchResponseEntity(HTTPS_WWW_TEAMROCKSTARS_NL_SITES_DEFAULT_FILES_SONGS_JSON, Array<SongDto>::class.java)
        } catch (e: Exception) {
            ResponseEntity.of(
                Optional.of(
                    objectMapper.readValue(
                        IOUtils.toString(javaClass.getResourceAsStream("/songs.json.json"), Charset.defaultCharset()),
                        Array<SongDto>::class.java
                    )
                )
            )
        }

    private fun <T> fetchResponseEntity(artistsUri: String, responseType: Class<T>): ResponseEntity<T> {
        val restTemplate = RestTemplate()
        val headers = HttpHeaders()
        headers.accept =
            listOf(MediaType.APPLICATION_JSON)
        val entity = HttpEntity("parameters", headers)
        return restTemplate.exchange(artistsUri, HttpMethod.GET, entity, responseType)
    }

    companion object {
        val SONG_DTO_PREDICATE = Predicate { songDto: SongDto ->
            songDto.genre
                .equals("Metal", ignoreCase = true)
        }
        const val HTTPS_WWW_TEAMROCKSTARS_NL_SITES_DEFAULT_FILES_ARTISTS_JSON =
            "https://www.teamrockstars.nl/sites/default/files/artists.json"
        const val HTTPS_WWW_TEAMROCKSTARS_NL_SITES_DEFAULT_FILES_SONGS_JSON =
            "https://www.teamrockstars.nl/sites/default/files/songs.json"
    }
}