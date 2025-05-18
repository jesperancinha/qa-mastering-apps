package org.jesperancinha.shop.config

import co.elastic.clients.elasticsearch.ElasticsearchClient
import co.elastic.clients.json.jackson.JacksonJsonpMapper
import co.elastic.clients.transport.rest_client.RestClientTransport
import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ElasticsearchConfig(
    @Value("\${elasticsearch.host}") private val host: String,
    @Value("\${elasticsearch.port}") private val port: Int,
    @Value("\${elasticsearch.index}") private val index: String,
    @Value("\${elasticsearch.shards}") private val shards: Int,
    @Value("\${elasticsearch.replicas}") private val replicas: Int
) {
    @Bean
    fun elasticsearchClient(): ElasticsearchClient {
        val restClient = RestClient.builder(HttpHost(host, port)).build()
        val transport = RestClientTransport(restClient, JacksonJsonpMapper())
        val elasticsearchClient = ElasticsearchClient(transport)
        elasticsearchClient.reset()
        elasticsearchClient.indices().create { c ->
            c.index(index)
                .settings { s ->
                    s.numberOfShards(shards.toString())
                        .numberOfReplicas(replicas.toString())
                }
        }
        return elasticsearchClient
    }
}

fun ElasticsearchClient.reset() {
    val exists = indices().exists { e -> e.index("products") }
    if (exists.value()) {
        indices().delete { it.index("products") }
    }
}