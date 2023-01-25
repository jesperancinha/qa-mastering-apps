package org.jesperancinha.books.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
internal class BooksConfiguration(
    @Value("\${books.url}")
    private val booksUrl: String
) {
    @Bean
    fun webClient(): WebClient = WebClient.builder()
        .baseUrl(booksUrl)
        .build();

}