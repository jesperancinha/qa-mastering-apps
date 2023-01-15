package org.jesperancinha.rockstarts.rockstarsmanager.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.jesperancinha.rockstarts.rockstarsmanager.containers.AbstractTestContainersIT.DockerPostgresDataInitializer
import org.jesperancinha.rockstarts.rockstarsmanager.data.ArtistDto
import org.jesperancinha.rockstarts.rockstarsmanager.services.ArtistsService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [ArtistsController::class])
@ActiveProfiles("test")
@ContextConfiguration(initializers = [DockerPostgresDataInitializer::class])
class ArtistsControllerTest {
    @MockBean
    private val artistsService: ArtistsService? = null

    @Autowired
    private val mockMvc: MockMvc? = null
    private val objectMapper = ObjectMapper()
    @Test
    @Throws(Exception::class)
    fun givenArtistOnDb_whenGettingArtist_thenReturnArtistNoError() {
        mockMvc!!.perform(MockMvcRequestBuilders.get("/rockstars/artists/1"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    @Throws(Exception::class)
    fun givenArtistOnDb_whenGetByName_thenGetArtistByName() {
        val artistDto: ArtistDto = ArtistDto.builder()
            .id(10101L)
            .name(DUA_LIPA)
            .build()
        Mockito.`when`(artistsService!!.getArtistsByName(DUA_LIPA)).thenReturn(artistDto)
        mockMvc!!.perform(MockMvcRequestBuilders.get("/artists/filter/name/DuaLipa"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @Throws(Exception::class)
    fun givenArtist_whenSave_thenCallSave() {
        val artistDto: ArtistDto = ArtistDto.builder()
            .id(10101L)
            .name(ARIANA_GRANDE)
            .build()
        Mockito.`when`(artistsService!!.saveArtist(ArgumentMatchers.any())).thenReturn(artistDto)
        mockMvc!!.perform(
            MockMvcRequestBuilders.post("/artists").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(artistDto))
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @Throws(Exception::class)
    fun givenArtistOnDb_whenUpdate_thenCallsUpdate() {
        val artistDto: ArtistDto = ArtistDto.builder()
            .id(10101L)
            .name(MABEL)
            .build()
        Mockito.`when`(artistsService!!.updateArtist(ArgumentMatchers.any())).thenReturn(artistDto)
        mockMvc!!.perform(
            MockMvcRequestBuilders.put("/artists/10101").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(artistDto))
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @Throws(Exception::class)
    fun givenArtistOnDb_whenDelete_thenCallsDelete() {
        mockMvc!!.perform(MockMvcRequestBuilders.delete("/artists/1"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    companion object {
        const val DUA_LIPA = "DuaLipa"
        const val ARIANA_GRANDE = "Ariana Grande"
        const val MABEL = "Mabel"
    }
}