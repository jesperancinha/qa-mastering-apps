package org.jesperancinha.books.rest

import org.jesperancinha.books.dao.BookRepositorySearchDao
import org.jesperancinha.books.domain.Book
import org.jesperancinha.books.domain.Results
import org.jesperancinha.books.domain.SearchResult
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
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
        @RequestParam("language") language: String
    ): List<SearchResult?> = bookRepositorySearchDao.findBooksByQueryAndLanguage(query, language)
        .toSearchResult()
}

private fun Book.toSearchResult() = this.volumeInfo?.let {
    SearchResult(
        title = this.volumeInfo.title,
        author = this.volumeInfo.authors?.joinToString(",") ?: "",
        isbn = this.volumeInfo.industryIdentifiers.joinToString(",") { it.identifier },
        publicationDate = this.volumeInfo.publishedDate?.toDate()
            ?.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.of("NL")))
            ?: ""
    )
}

fun String.toDate(): LocalDate? = when (this.length) {
    4 -> LocalDate.of(this.toInt(), 1, 1)
    8 -> LocalDate.parse(this)
    else -> null
}

private fun Results.toSearchResult() =
    this.items.sortedByDescending { it.volumeInfo?.publishedDate }.mapNotNull { it.toSearchResult() }

