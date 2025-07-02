package org.jesperancinha.airports.services

import org.jesperancinha.airports.containers.MainContainerService
import org.jesperancinha.airports.pojos.Airport
import org.springframework.stereotype.Service

@Service
class QueryAirportService(
    val mainContainerService: MainContainerService
) {

    fun getAirportsByCountryName(countryName: String): List<Airport> {
        return mainContainerService.fullAirportInfo
            .filter { airport: Airport ->
                airport.country != null && countryName.equals(
                    airport.country?.name,
                    ignoreCase = true
                )
            }
    }

    fun getAirportsByCountryCode(countryCode: String): List<Airport> {
        return mainContainerService.fullAirportInfo
            .filter { airport: Airport -> countryCode.equals(airport.isoCountry, ignoreCase = true) }
    }
}