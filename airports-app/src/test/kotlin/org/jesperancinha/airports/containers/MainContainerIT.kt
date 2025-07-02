package org.jesperancinha.airports.containers

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import org.apache.camel.test.spring.junit5.CamelSpringBootTest
import org.jesperancinha.airports.pojos.Airport
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext

@CamelSpringBootTest
@EnableAutoConfiguration
@SpringBootTest
@DirtiesContext
@Disabled
class MainContainerIT @Autowired constructor(
    val mainContainerService: MainContainerService
) {

    @Test
    fun `should run main test container`() {
        val airports: List<Airport> = mainContainerService.fullAirportInfo
        val airport = airports[0]

        airports.shouldHaveSize(46506)
        airport.shouldNotBeNull()
        airport.id.shouldNotBeNull()
        airport.country.shouldNotBeNull()
    }
}