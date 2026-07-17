package org.jesperancinha.airports.pojos

import com.fasterxml.jackson.annotation.JsonProperty

data class Airport(
    @param:JsonProperty("id")
    val id: String? = null,
    @param:JsonProperty("ident")
    val ident: String? = null,
    @param:JsonProperty("type")
    val type: String? = null,
    @param:JsonProperty("name")
    val name: String? = null,
    @param:JsonProperty("latitudeDeg")
    val latitudeDeg: String? = null,
    @param:JsonProperty("longitudeDeg")
    val longitudeDeg: String? = null,
    @param:JsonProperty("elevationFt")
    val elevationFt: String? = null,
    @param:JsonProperty("continent")
    val continent: String? = null,
    @param:JsonProperty("isoCountry")
    val isoCountry: String,
    @param:JsonProperty("isoRegion")
    val isoRegion: String? = null,
    @param:JsonProperty("municipality")
    val municipality: String? = null,
    @param:JsonProperty("scheduledService")
    val scheduledService: String? = null,
    @param:JsonProperty("gpsCode")
    val gpsCode: String? = null,
    @param:JsonProperty("iataCode")
    val iataCode: String? = null,
    @param:JsonProperty("localCode")
    val localCode: String? = null,
    @param:JsonProperty("homeLink")
    val homeLink: String? = null,
    @param:JsonProperty("wikipediaLink")
    val wikipediaLink: String? = null,
    @param:JsonProperty("keywords")
    val keywords: String? = null,
    @param:JsonProperty("country")
    var country: Country?=null,
) {
    val runways: MutableList<Runway> = mutableListOf()

    fun addRunWay(runway: Runway) {
        runways.add(runway)
    }
}