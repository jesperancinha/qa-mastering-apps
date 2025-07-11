package org.jesperancinha.airports.containers

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import org.jesperancinha.airports.pojos.Country
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class CountryContainerTest {
    @Test
    fun `should run country test container`() {
        val airportContainer = CountryContainer()
        val countries: List<Country> = airportContainer.countries
        val country = countries[0]
        countries.shouldHaveSize(248)
        country.shouldNotBeNull()
        country.id.shouldNotBeNull()
    }
}