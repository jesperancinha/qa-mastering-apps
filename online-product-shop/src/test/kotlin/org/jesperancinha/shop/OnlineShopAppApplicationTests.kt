package org.jesperancinha.shop

import co.elastic.clients.elasticsearch.ElasticsearchClient
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest
@MockitoBean(types = [ElasticsearchClient::class, ElasticsearchTemplate::class])
class OnlineShopAppApplicationTests {
    @Test
    fun contextLoads() {
    }
}
