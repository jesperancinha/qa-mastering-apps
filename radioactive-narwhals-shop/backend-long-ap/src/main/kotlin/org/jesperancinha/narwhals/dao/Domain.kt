package org.jesperancinha.narwhals.dao

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class SoldItems(
    val seaCabbage: Long,
    val tusks: Long,
) {
    companion object {
        operator fun invoke() = SoldItems(0,0)
    }
}

data class EffectiveStock(
    val seaCabbage: Long,
    val tusks: Long,
)

data class CustomerOrder(
    @param:JsonProperty
    val customer: String,
    @param:JsonProperty
    val order: Order,
)

data class Order(
    @param:JsonProperty
    val seaCabbage: Long = 0,
    @param:JsonProperty
    val tusks: Long = 0,
)

data class OrderResponse(
    @param:JsonProperty
    val seaCabbage: BigDecimal,
    @param:JsonProperty
    val tusks: Long,
    @param:JsonProperty("seaCabbage-available-in-days")
    val cabbagesAvailableInDays: Int = 0,
    @param:JsonProperty("tusks-available-in-days")
    val tusksAvailableInDays: Int = 0,
)