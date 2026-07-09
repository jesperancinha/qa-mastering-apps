package org.jesperancinha.narwhals.rest

import org.jesperancinha.narwhals.dao.CustomerOrder
import org.jesperancinha.narwhals.dao.NarwhalsWebShopDao
import org.jesperancinha.narwhals.safe.CurrentNarwhals
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
internal class NarwhalsShopController(
    val narwhalsWebShopDao: NarwhalsWebShopDao,
) {
    @PostMapping(
        value = ["/load"],
        consumes = [MediaType.APPLICATION_XML_VALUE]
    )
    suspend fun loadNarwhals(@RequestBody narwhals: org.jesperancinha.narwhals.safe.XmlNarwhals): ResponseEntity<String> =
        narwhalsWebShopDao.loadWebShop(narwhals)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @GetMapping(
        value = ["/stock/{days}"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    suspend fun getStocks(@PathVariable days: Int) = narwhalsWebShopDao.findStocks(days)

    @GetMapping(
        value = ["/narwhals/{days}"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    suspend fun getNarwhalss(@PathVariable days: Int): CurrentNarwhals = narwhalsWebShopDao.findNarwhals(days)

    @PostMapping(
        value = ["/order/{days}"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    suspend fun orders(@RequestBody customerOrder: CustomerOrder, @PathVariable days: Int) =
        narwhalsWebShopDao.order(customerOrder, days - 1)

}