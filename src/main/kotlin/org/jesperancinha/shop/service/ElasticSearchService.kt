package org.jesperancinha.shop.service

import co.elastic.clients.elasticsearch.ElasticsearchClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.jesperancinha.shop.dto.ProductDto
import org.springframework.stereotype.Service

@Service
class ElasticsearchService(
    private val esClient: ElasticsearchClient
) {

    suspend fun indexProduct(product: ProductDto) {
        esClient.index {
            it.index("products")
                .id(product.id.toString())
                .document(product)
        }
    }

    suspend fun searchProducts(query: String): Flow<ProductDto> = flow {
        val response = withContext(Dispatchers.IO) {
            esClient.search({ it
                .index("products")
                .query { q -> q
                    .multiMatch { mm ->
                        mm.query(query)
                            .fields("name", "description", "category", "price")
                    }
                }
            }, ProductDto::class.java)
        }

        for (hit in response.hits().hits()) {
            emit(hit.source() as ProductDto)
        }
    }

    fun reset() {
        val exists = esClient.indices().exists { e -> e.index("products") }
        if (exists.value()) {
            esClient.indices().delete { it.index("products") }
        }
    }
}