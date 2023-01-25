package org.jesperancinha.books.rest

import org.jesperancinha.books.dao.BookRepositorySearchDao
import org.jesperancinha.books.domain.Book
import org.jesperancinha.books.domain.Language
import org.jesperancinha.books.domain.Results
import org.jesperancinha.books.domain.SearchResult
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

@RestController
@RequestMapping("/")
class Controller(
    val bookRepositorySearchDao: BookRepositorySearchDao
) {

    /**
     * http://localhost:8080/zyTCAlFPjgYC
     */
    @GetMapping("{volume}")
    suspend fun getSpecificVolume(
        @PathVariable("volume") volume: String
    ): SearchResult? = bookRepositorySearchDao.findBookByVolume(volume).toSearchResult()

    /**
     * https://www.googleapis.com/books/v1/volumes?q=love&maxResults=2&langRestrict=fr
     */
    @GetMapping
    suspend fun get10VolumesByTextAndLanguage(
        @RequestParam("query") query: String,
        @RequestParam("language") language: Language
    ): List<SearchResult?> = bookRepositorySearchDao.findBooksByQueryAndLanguage(query, language)
        .toSearchResult()
}

private fun Book.toSearchResult() = this.volumeInfo?.let { volumeInfo ->
    SearchResult(
        title = volumeInfo.title,
        author = volumeInfo.authors?.joinToString(",") ?: "",
        isbn = volumeInfo.industryIdentifiers?.joinToString(",") { it.identifier } ?: "",
        publicationDate = volumeInfo.publishedDate?.toDate()
            ?.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.of("NL")))
            ?: ""
    )
}

fun String.toDate(): LocalDate? = when (this.length) {
    4 -> LocalDate.of(this.toInt(), 1, 1)
    7 -> try { this.toDate("yyyy-MM") }  catch (_:Exception) { this.toDate("yyyy/MM") }
    10 -> try { LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd")) }  catch (_:Exception) { LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy/MM/dd")) }
    else -> null
}

private fun String.toDate(format: String): LocalDate = YearMonth.parse(this, DateTimeFormatter.ofPattern(format)).atDay(1)

private fun Results.toSearchResult() =
    this.items.mapNotNull { it.toSearchResult() }.sortedByDescending { it.publicationDate }

