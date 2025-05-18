package org.jesperancinha.shop.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.math.BigDecimal

@Table("products")
data class Product(
    @Id val id: Long? = null,
    val name: String,
    val description: String,
    val category: String,
    val price: BigDecimal,
)

interface ProductRepository : CoroutineCrudRepository<Product, Long> {
   suspend fun findProductByNameIgnoreCase(name: String): Product?
}
