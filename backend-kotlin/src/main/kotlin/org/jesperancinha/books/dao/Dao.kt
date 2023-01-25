package org.jesperancinha.books.dao

import org.jesperancinha.books.domain.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody


@Service
class BookRepositorySearchDao(
    @Autowired
    private val webClient: WebClient
){
    suspend fun  findBookByVolune(volume: String): Book = webClient
        .method(HttpMethod.GET).uri("https://www.googleapis.com/books/v1/volumes/$volume")
        .retrieve()
        .awaitBody()

}