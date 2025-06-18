package org.jesperancinha.rockstarts.rockstarsmanager.controller

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.shouldNotBe
import org.assertj.core.api.Assertions
import org.jesperancinha.rockstarts.rockstarsmanager.containers.AbstractTestContainersIT.DockerPostgresDataInitializer
import org.jesperancinha.rockstarts.rockstarsmanager.data.ArtistDto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ContextConfiguration(initializers = [DockerPostgresDataInitializer::class])
@Transactional
class ArtistsControllerIT @Autowired constructor(
    @Autowired
    private val controller: ArtistsController,
) {

    @Test
    fun givenArtistOnDb_whenGettingArtist_thenReturnArtistNoError() {
        val artistsById = controller.getArtistsById(123L)
        Assertions.assertThat(artistsById).isNotNull
        Assertions.assertThat(artistsById.body).isNull()
        Assertions.assertThat(artistsById.statusCode).isEqualTo(NOT_FOUND)
    }

    @Test
    fun givenArtistOnDb_whenGetByName_thenGetArtistByName() {
        val artistDto = ArtistDto(
            id = 10101L,
            name = DUA_LIPA
        )
        val savedToDataBase = controller.saveArtist(artistDto)
        val artistsByName = controller.getArtistByName(DUA_LIPA)
        savedToDataBase.shouldNotBeNull()
        savedToDataBase.name shouldBe artistDto.name
        artistsByName.shouldNotBeNull()
        artistsByName.statusCode shouldBe OK
        val artistsByNameBody = artistsByName.body
        artistsByNameBody.shouldNotBeNull()
        artistsByNameBody.name shouldBe artistDto.name
    }

    @Test
    fun givenArtist_whenSave_thenSaveArtist() {
        val artistDto = ArtistDto(
            id = 10102L,
            name = ARIANA_GRANDE)
        val savedToDataBase = controller.saveArtist(artistDto)
        savedToDataBase.shouldNotBeNull()
        savedToDataBase.name shouldBe artistDto.name
        savedToDataBase.id shouldBe 1L
    }

    @Test
    fun given2Artists_whenSave_thenSave2Artists() {
        val artistDtoAriana = ArtistDto(
            id =10102L,
            name = ARIANA_GRANDE)
        val artistDtoMabel = ArtistDto(
            id =10101L,
            name =MABEL)
        val savedAriana = controller.saveArtist(artistDtoAriana)
        val savedMabel = controller.saveArtist(artistDtoMabel)
        savedAriana.name shouldBe artistDtoAriana.name
        savedMabel.name shouldBe artistDtoMabel.name
        savedAriana.name shouldNotBe savedMabel.name
    }

    @Test
    fun givenArtistOnDb_whenUpdate_thenUpdate() {
        val artistDto = ArtistDto(
             name = ARIANA_GRANDE)
        val savedToDataBase = controller.saveArtist(artistDto)
        val artistDtoUpdate = ArtistDto(
             id = savedToDataBase.id,
             name = MABEL)
        val artistDtoUpdateResult = controller.putArtist(artistDtoUpdate, artistDtoUpdate.id)
        savedToDataBase.shouldNotBeNull()
        savedToDataBase.name shouldBe artistDto.name
        artistDtoUpdate.shouldNotBeNull()
        artistDtoUpdateResult.shouldNotBeNull()
        artistDtoUpdateResult.name shouldBe artistDtoUpdate.name
        artistDtoUpdateResult.id shouldBe savedToDataBase.id
    }

    @Test
    fun givenArtistOnDb_whenDelete_thenCallsDelete() {
        val artistDto = ArtistDto(
            id = 10102L,
            name= ARIANA_GRANDE)
        val savedToDataBase = controller.saveArtist(artistDto)
        savedToDataBase.id?.let { controller.deleteById(it) }
        val artistsById = savedToDataBase.id?.let { controller.getArtistsById(it) }
        savedToDataBase.name shouldBe artistDto.name
        artistsById.shouldNotBeNull()
        artistsById.body.shouldBeNull()
        artistsById.statusCode shouldBe NOT_FOUND
    }

    companion object {
        const val DUA_LIPA = "DuaLipa"
        const val ARIANA_GRANDE = "Ariana Grande"
        const val MABEL = "Mabel"
    }
}