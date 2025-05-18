package org.jesperancinha.shop.rest

import kotlinx.coroutines.flow.Flow
import org.jesperancinha.shop.domain.Product
import org.jesperancinha.shop.dto.ProductDto
import org.jesperancinha.shop.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {

    @PostMapping
    suspend fun create(@RequestBody product: ProductDto): Product {
        return productService.saveProduct(product)
    }

    @GetMapping("/search")
    suspend fun search(@RequestParam("q") query: String): Flow<ProductDto> =
        productService.searchProducts(query)
}