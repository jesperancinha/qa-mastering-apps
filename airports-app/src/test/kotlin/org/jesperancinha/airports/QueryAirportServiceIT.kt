package org.jesperancinha.airports

import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.apache.camel.test.spring.junit5.CamelSpringBootTest
import org.jesperancinha.airports.pojos.Airport
import org.jesperancinha.airports.services.QueryAirportService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext

@CamelSpringBootTest
@EnableAutoConfiguration
@SpringBootTest
@DirtiesContext
class QueryAirportServiceIT @Autowired constructor(
    private val queryAirportService: QueryAirportService
) {

    @Test
    fun `should get airports by name`() {
        queryAirportService.shouldNotBeNull()
        val airports: List<Airport> = queryAirportService.getAirportsByCountryName("Portugal")
        airports.shouldNotBeNull()
    }

    @Test
    fun `should get airports by country code`() {
        val airports: List<Airport> = queryAirportService.getAirportsByCountryCode("PT")

        airports.shouldNotBeEmpty()
        airports.forEach { airport -> airport.isoCountry shouldBe "PT" }
    }
}