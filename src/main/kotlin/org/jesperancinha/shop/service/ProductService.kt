package org.jesperancinha.shop.service

import kotlinx.coroutines.flow.Flow
import org.jesperancinha.shop.domain.Product
import org.jesperancinha.shop.domain.ProductRepository
import org.jesperancinha.shop.dto.ProductDto
import org.jesperancinha.shop.dto.toEntity
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepo: ProductRepository,
    private val elasticsearchService: ElasticsearchService
) {
    suspend fun saveProduct(product: ProductDto): Product {
        val savedProduct = productRepo.save(product.toEntity())
        elasticsearchService.indexProduct(product = savedProduct)  // Dual-write
        return savedProduct
    }
    suspend fun searchProducts(query: String): Flow<ProductDto> =
        elasticsearchService.searchProducts(query)
}