package org.jesperancinha.books.configuration

import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
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
        .build()

    @Bean
    fun cacheManager(): CacheManager = ConcurrentMapCacheManager("books")

    @Bean
    fun hazelcastInstance() = Config().apply { clusterName = "books" }.let {  Hazelcast.newHazelcastInstance(it) }
}
