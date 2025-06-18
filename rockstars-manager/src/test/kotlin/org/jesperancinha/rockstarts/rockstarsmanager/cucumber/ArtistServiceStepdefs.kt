package org.jesperancinha.rockstarts.rockstarsmanager.cucumber

import io.cucumber.datatable.DataTable
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions
import org.jesperancinha.rockstarts.rockstarsmanager.converters.toData
import org.jesperancinha.rockstarts.rockstarsmanager.data.ArtistDto
import org.jesperancinha.rockstarts.rockstarsmanager.repository.ArtistsRepository
import org.jesperancinha.rockstarts.rockstarsmanager.services.ArtistsService
import org.mockito.Mockito
import java.util.function.Consumer
import java.util.stream.Collectors

class ArtistServiceStepdefs {
    private val artistsRepository = Mockito.mock(ArtistsRepository::class.java)
    private val artistsService = ArtistsService(artistsRepository)
    private var artistDtos: List<ArtistDto> = emptyList()
    private val results: MutableList<ArtistDto?> = ArrayList()

    @Given("^the following artist:$")
    fun the_following_artist(artistDtos: DataTable) {
        this.artistDtos = artistDtos
            .asLists()
            .stream()
            .filter { artist: List<String> -> artist[0] != "id" }
            .map { artist: List<String> -> ArtistDto(artist[0].toLong(), artist[1]) }
            .collect(Collectors.toList())
    }

    @When("^calling save method$")
    fun calling_save_method() {
        artistDtos.forEach { artistDto: ArtistDto ->
            val artist = artistDto.toData()
            Mockito.`when`(artistsRepository.save(artist)).thenReturn(artist)
            val result = artistsService.saveArtist(artistDto)
            results.add(result)
        }
    }

    @Then("^we get artist with names:$")
    fun we_get_artist_with_names(expectedArtistNames: List<String>) {
        Assertions.assertThat(expectedArtistNames).hasSameElementsAs(expectedArtistNames)
        for (i in artistDtos.indices) {
            artistDtos[i].name shouldBe expectedArtistNames[i]
        }
    }
}