package org.jesperancinha.shop.rest

import kotlinx.coroutines.flow.Flow
import org.jesperancinha.shop.dto.ProductDto
import org.jesperancinha.shop.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {

    @PostMapping
    suspend fun create(@RequestBody product: ProductDto): ProductDto {
        return productService.saveProduct(product)
    }

    @GetMapping("/search")
    suspend fun search(@RequestParam("q") query: String): Flow<ProductDto> =
        productService.searchProducts(query)

    @PostMapping("/reset")
            suspend fun reset() = productService.reset()
}