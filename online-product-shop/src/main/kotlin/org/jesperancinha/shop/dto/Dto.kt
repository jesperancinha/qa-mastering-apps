package org.jesperancinha.shop.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.jesperancinha.shop.domain.Product
import java.math.BigDecimal

data class ProductDto @JsonCreator constructor(
    @param:JsonProperty("id") val id: Long?,
    @param:JsonProperty("name") val name: String,
    @param:JsonProperty("description") val description: String,
    @param:JsonProperty("category") val category: String,
    @param:JsonProperty("price") val price: String,
)

fun ProductDto.toEntity(): Product = Product(
    id = id,
    name = name,
    description = description,
    category = category,
    price = BigDecimal(price)
)

fun Product.toDto(): ProductDto = ProductDto(
    id = id,
    name = name,
    description = description,
    category = category,
    price = price.toPlainString()
)