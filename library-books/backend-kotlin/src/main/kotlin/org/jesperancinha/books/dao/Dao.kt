package org.jesperancinha.books.dao

import com.hazelcast.core.HazelcastInstance
import org.jesperancinha.books.domain.Book
import org.jesperancinha.books.domain.Language
import org.jesperancinha.books.domain.Results
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody


@Service
class BookRepositorySearchDao(
    @Autowired
    private val webClient: WebClient,
    @Autowired
    private val hazelcastInstance: HazelcastInstance
) {

    fun mapVolumes(): MutableMap<String, Book> = hazelcastInstance.getMap("volumes")
    fun mapQueries(): MutableMap<String, Results> = hazelcastInstance.getMap("queries")

    suspend fun findBookByVolume(volume: String): Book = mapVolumes()[volume] ?: webClient
        .method(HttpMethod.GET).uri("/$volume")
        .retrieve()
        .awaitBody<Book>()
        .also { mapVolumes()[volume] = it }

    suspend fun findBooksByQueryAndLanguage(query: String, language: Language): Results = mapQueries()["$query+$language"] ?: webClient
        .method(HttpMethod.GET).uri("?q=${query}&maxResults=10&langRestrict=${language.toString().lowercase()}")
        .retrieve()
        .awaitBody<Results>()
        .also { mapQueries()["$query+$language"] = it }
}