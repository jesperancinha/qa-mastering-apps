package org.jesperancinha.books.rest

import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import org.jesperancinha.books.dao.BookRepositorySearchDao
import org.jesperancinha.books.domain.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerIntegrationTest @Autowired constructor(
    private val webTestClient: WebTestClient,
    @MockkBean(relaxed = true)
    private val bookRepositorySearchDao: BookRepositorySearchDao
) {

    @Test
    fun `should get specific volume via integration`() {
        val testBook = createTestBook("123", "Integration Test Book", listOf("Author A"), "2021-01-01")
        
        coEvery { bookRepositorySearchDao.findBookByVolume("123") } returns testBook

        webTestClient.get().uri("/123")
            .exchange()
            .expectStatus().isOk
            .expectBody(SearchResult::class.java)
            .value { result ->
                result.title shouldBe "Integration Test Book"
                result.author shouldBe "Author A"
                result.isbn shouldBe "ISBN-123"
                result.publicationDate shouldBe "01 januari 2021"
            }

    }

    @Test
    fun `should get volumes by query via integration`() {
        val results = Results(
            kind = "Kind",
            totalItems = 1L,
            items = listOf(createTestBook("1", "Title 1", listOf("Author 1"), "2020"))
        )
        
        coEvery { bookRepositorySearchDao.findBooksByQueryAndLanguage("test", Language.en) } returns results

        webTestClient.get().uri { it.path("/").queryParam("query", "test").queryParam("language", "en").build() }
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$").isArray
            .jsonPath("$.length()").isEqualTo(1)
            .jsonPath("$[0].title").isEqualTo("Title 1")
            .jsonPath("$[0].publicationDate").isEqualTo("01 januari 2020")

    }

    @Test
    fun `should handle corner case with multiple authors and different date format`() {
        val testBook = createTestBook("456", "Multi Author Book", listOf("Author 1", "Author 2"), "2022-05")
        
        coEvery { bookRepositorySearchDao.findBookByVolume("456") } returns testBook

        webTestClient.get().uri("/456")
            .exchange()
            .expectStatus().isOk
            .expectBody(SearchResult::class.java)
            .value { result ->
                result.author shouldBe "Author 1,Author 2"
                result.publicationDate shouldBe "01 mei 2022"
            }
    }

    @Test
    fun `should handle corner case with missing authors and identifiers`() {
         val testBook = Book(
            id = "789",
            kind = "AKind",
            etag = "eTag",
            accessInfo = null,
            layerInfo = null,
            volumeInfo = VolumeInfo(
                title = "Minimal Book",
                authors = null,
                publishedDate = null,
                industryIdentifiers = null,
                allowAnonLogging = true,
                canonicalVolumeLink = "",
                contentVersion = "",
                dimensions = null,
                infoLink = "",
                maturityRating = "",
                pageCount = 0,
                previewLink = "",
                printType = "",
                printedPageCount = 0L,
                publisher = "Publisher",
                readingModes = ReadingModes(text = true, image = true)
            ),
            saleInfo = null,
            selflink = null
        )
        
        coEvery { bookRepositorySearchDao.findBookByVolume("789") } returns testBook

        webTestClient.get().uri("/789")
            .exchange()
            .expectStatus().isOk
            .expectBody(SearchResult::class.java)
            .value { result ->
                result.author shouldBe ""
                result.isbn shouldBe ""
                result.publicationDate shouldBe ""
            }
    }

    private fun createTestBook(id: String, title: String, authors: List<String>, publishedDate: String) = Book(
        id = id,
        kind = "AKind",
        etag = "eTag",
        accessInfo = null,
        layerInfo = null,
        volumeInfo = VolumeInfo(
            title = title,
            authors = authors,
            publishedDate = publishedDate,
            industryIdentifiers = listOf(
                IndustryIdentifier(type = "ISBN", identifier = "ISBN-$id")
            ),
            allowAnonLogging = true,
            canonicalVolumeLink = "",
            contentVersion = "",
            dimensions = null,
            infoLink = "",
            maturityRating = "",
            pageCount = 0,
            previewLink = "",
            printType = "",
            printedPageCount = 0L,
            publisher = "Publisher",
            readingModes = ReadingModes(text = true, image = true)
        ),
        saleInfo = null,
        selflink = null
    )
}
