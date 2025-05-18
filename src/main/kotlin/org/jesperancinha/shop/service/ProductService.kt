package org.jesperancinha.shop.service

import kotlinx.coroutines.flow.Flow
import org.jesperancinha.shop.domain.ProductRepository
import org.jesperancinha.shop.dto.ProductDto
import org.jesperancinha.shop.dto.toDto
import org.jesperancinha.shop.dto.toEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val productRepo: ProductRepository,
    private val elasticsearchService: ElasticsearchService
) {
    @Transactional
    suspend fun saveProduct(product: ProductDto): ProductDto {
        val savedProduct = productRepo.findProductByNameIgnoreCase(product.name)
            ?.run {
                productRepo.save(this.toDto().copy(price = product.price).toEntity())
            }
            ?: productRepo.save(product.toEntity())
        val productDto = savedProduct.toDto()
        elasticsearchService.indexProduct(product = productDto)
        return productDto
    }

    fun searchProducts(query: String): Flow<ProductDto> =
        elasticsearchService.searchProducts(query)

    @Transactional
    suspend fun reset() {
        elasticsearchService.reset()
        productRepo.deleteAll()
    }
}