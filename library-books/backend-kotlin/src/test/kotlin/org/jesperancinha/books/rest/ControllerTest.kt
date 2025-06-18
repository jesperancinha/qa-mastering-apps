package org.jesperancinha.books.rest

import com.ninjasquad.springmockk.MockkBean
import io.kotest.common.runBlocking
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import org.jesperancinha.books.dao.BookRepositorySearchDao
import org.jesperancinha.books.domain.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ControllerTest @Autowired constructor(
    val controller: Controller
) {

    @MockkBean(relaxed = true)
    lateinit var bookRepositorySearchDao: BookRepositorySearchDao

    @Test
    fun `should be able to make a request to get one volume`() = runBlocking {
        val testBook = Book(
            id = "123",
            kind = "AKind",
            etag = "eTag",
            accessInfo = null,
            layerInfo = null,
            volumeInfo = VolumeInfo(
                title = "Honey I shrunk up the cats!",
                authors = listOf("Big Cat"),
                publishedDate = "2007",
                industryIdentifiers = listOf(
                    IndustryIdentifier(
                        type = "Purr",
                        identifier = "00112233445566778899"
                    )
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
                publisher = "Woha publishing",
                readingModes = ReadingModes(
                    text = true,
                    image = true
                )

            ),
            saleInfo = null,
            selflink = null
        )
        coEvery { bookRepositorySearchDao.findBookByVolume("12345678") } returns testBook
        runBlocking {
            controller.getSpecificVolume("12345678")
                .shouldNotBeNull()
                .apply {
                    title shouldBe "Honey I shrunk up the cats!"
                    author shouldBe "Big Cat"
                    isbn shouldBe "00112233445566778899"
                    publicationDate shouldBe "01 januari 2007"
                }
        }
        coVerify { bookRepositorySearchDao.findBookByVolume("12345678") }
    }

    @Test
    fun `should get 10 volumes per search query and language`() {
        val results = Results(
            kind = "Cheeses",
            totalItems = 1L,
            items = listOf(
                Book(
                    id = "123",
                    kind = "AKind",
                    etag = "eTag",
                    accessInfo = null,
                    layerInfo = null,
                    volumeInfo = VolumeInfo(
                        title = "Honey I shrunk up the cats!",
                        authors = listOf("Big Cat"),
                        publishedDate = "2007",
                        industryIdentifiers = listOf(
                            IndustryIdentifier(
                                type = "Purr",
                                identifier = "00112233445566778899"
                            )
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
                        publisher = "Woha publishing",
                        readingModes = ReadingModes(
                            text = true,
                            image = true
                        )

                    ),
                    saleInfo = null,
                    selflink = null
                )
            )
        )
        coEvery { bookRepositorySearchDao.findBooksByQueryAndLanguage("dance", Language.nl) } returns results
        runBlocking {
            controller.get10VolumesByTextAndLanguage("dance", Language.nl)
                .shouldNotBeNull()
                .shouldHaveSize(1)
                .first()
                .shouldNotBeNull()
                .apply {
                    title shouldBe "Honey I shrunk up the cats!"
                    author shouldBe "Big Cat"
                    isbn shouldBe "00112233445566778899"
                    publicationDate shouldBe "01 januari 2007"
                }
        }
        coVerify { bookRepositorySearchDao.findBooksByQueryAndLanguage("dance", Language.nl) }
    }


}