package org.jesperancinha.shop

import co.elastic.clients.elasticsearch.ElasticsearchClient
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest
class OnlineShopAppApplicationTests @Autowired constructor(
    @MockitoBean
    private val elasticsearchClient: ElasticsearchClient,
    @MockitoBean
    private val elasticSearchTemplate: ElasticsearchTemplate
) {

    @Test
    fun contextLoads() {
    }

}
