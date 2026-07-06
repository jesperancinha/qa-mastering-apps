package org.jesperancinha.narwhals.dao

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class SoldItems(
    val seaCabbage: BigDecimal,
    val tusks: Int,
)

data class EffectiveStock(
    val seaCabbage: BigDecimal,
    val tusks: Int,
)

data class CustomerOrder(
    @param:JsonProperty
    val customer: String,
    @param:JsonProperty
    val order: Order,
)

data class Order(
    @param:JsonProperty
    val seaCabbage: BigDecimal = BigDecimal.ZERO,
    @param:JsonProperty
    val tusks: Int = 0,
)

data class OrderResponse(
    @param:JsonProperty
    val seaCabbage: BigDecimal,
    @param:JsonProperty
    val tusks: Int,
    @param:JsonProperty("seaCabbage-available-in-days")
    val cabbagesAvailableInDays: Int = 0,
    @param:JsonProperty("tusks-available-in-days")
    val tusksAvailableInDays: Int = 0,
)