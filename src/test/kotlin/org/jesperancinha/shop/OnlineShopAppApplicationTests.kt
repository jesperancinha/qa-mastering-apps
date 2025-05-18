package org.jesperancinha.shop

import co.elastic.clients.elasticsearch.ElasticsearchClient
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest
class OnlineShopAppApplicationTests {

    @MockitoBean
    lateinit var elasticsearchClient: ElasticsearchClient

    @MockitoBean
    lateinit var elasticSearchTemplate: ElasticsearchTemplate

    @Test
    fun contextLoads() {
    }

}
