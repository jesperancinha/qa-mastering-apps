package org.jesperancinha.airports.containers

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import org.jesperancinha.airports.pojos.Country
import java.io.InputStreamReader

class CountryContainer {
    private val mapper by lazy { CsvMapper() }
    private val schema by lazy {
        CsvSchema.builder()
            .setColumnSeparator(',')
            .addColumn("id")
            .addColumn("code")
            .addColumn("name")
            .addColumn("continent")
            .addColumn("wikipediaLink")
            .addColumn("keywords")
            .build()
    }
    
    val countries by lazy {
        javaClass.getResourceAsStream("/countries.csv")?.use { stream ->
            mapper
                .reader(Country::class.java)
                .with(schema)
                .readValues<Country>(InputStreamReader(stream))
                .readAll()
        } ?: emptyList()
    }
}