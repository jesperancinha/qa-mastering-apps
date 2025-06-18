package org.jesperancinha.rockstarts.rockstarsmanager.services

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.jesperancinha.rockstarts.rockstarsmanager.data.ArtistDto
import org.jesperancinha.rockstarts.rockstarsmanager.model.Artist
import org.jesperancinha.rockstarts.rockstarsmanager.repository.ArtistsRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import org.springframework.data.repository.findByIdOrNull
import java.util.*

@ExtendWith(MockKExtension::class)
internal class ArtistsServiceTest {
    @InjectMockKs
    lateinit var artistsService: ArtistsService

    @MockK(relaxed = true)
    lateinit var artistsRepository: ArtistsRepository

    @Test
    fun givenNoArtistOnDb_whenGettingArtist_thenReturnNullNoError() {
        every { artistsRepository.findByIdOrNull(1L) } returns null
        val artistsById = artistsService.getArtistsById(1L)
        artistsById.shouldBeNull()
    }

    @Test
    fun givenArtistOnDb_whenGettingArtist_thenReturnArtistNoError() {
        val mock = mockk<Artist>()
        every { mock.id } returns 10101L
        every { mock.name } returns ARIANA_GRANDE
        every { artistsRepository.findById(1L) } returns Optional.of(mock)
        val artistsById = artistsService.getArtistsById(1L)
        artistsById.shouldNotBeNull()
        artistsById.id shouldBe mock.id
        artistsById.name shouldBe mock.name
        verify { artistsRepository.findById(1L) }
    }

    @Test
    fun givenArtistOnDb_whenGetByName_thenGetArtistByName() {
        val mock = mockk<Artist>()
        every { mock.id } returns 10101L
        every { mock.name } returns DUA_LIPA
        every { artistsRepository.findArtistsByName(DUA_LIPA) } returns mock
        val artistDto = artistsService.getArtistsByName(DUA_LIPA)
        artistDto.id shouldBe mock.id
        artistDto.name shouldBe mock.name
        verify { artistsRepository.findArtistsByName(DUA_LIPA) }
    }

    @Test
    fun givenArtist_whenSave_thenCallSave() {
        val mock = mockk<Artist>()
        every { mock.id } returns 10101L
        every { mock.name } returns MABEL
        val dto = mockk<ArtistDto>()
        every { dto.id } returns (10101L)
        every { dto.name } returns (MABEL)
        every { artistsRepository.save(any()) } returns mock
        val artistDto = artistsService.saveArtist(dto)
        artistDto.id shouldBe dto.id
        artistDto.name shouldBe dto.name
        val slotSavedArtist = slot<Artist>()
        verify {
            artistsRepository.save(
                capture(slotSavedArtist)
            )
        }
        val value = slotSavedArtist.captured
        value.id shouldBe dto.id
        value.name shouldBe dto.name
    }

    @Test
    fun givenArtistOnDb_whenUpdate_thenCallsUpdate() {
        val mock = mockk<Artist>()
        every { mock.id } returns 10101L
        every { mock.name } returns MABEL
        val dto = mockk<ArtistDto>()
        every { dto.id } returns (10101L)
        every { dto.name } returns (MABEL)
        every { artistsRepository.save(any()) } returns mock
        val artistDto = artistsService.updateArtist(dto)
        artistDto.id shouldBe dto.id
        artistDto.name shouldBe dto.name
        val slotSavedArtist = slot<Artist>()
        verify {
            artistsRepository.save(
                capture(slotSavedArtist)
            )
        }
        val value = slotSavedArtist.captured
        value.id shouldBe dto.id
        value.name shouldBe dto.name
    }

    @Test
    fun givenArtistOnDb_whenDelete_thenCallsDelete() {
        artistsService.deleteById(1L)
        verify { artistsRepository.deleteById(1L) }
    }

    companion object {
        const val DUA_LIPA = "Dua Lipa"
        const val ARIANA_GRANDE = "Ariana Grande"
        const val MABEL = "Mabel"
    }
}