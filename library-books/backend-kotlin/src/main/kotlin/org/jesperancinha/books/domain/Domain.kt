package org.jesperancinha.books.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

typealias Author = String

typealias Publisher = String?

typealias Category = String

data class Results(
    @param:JsonProperty
    val kind: String,
    @param:JsonProperty
    val totalItems: Long,
    @param:JsonProperty
    val items: List<Book>
)

data class Book(
    @param:JsonProperty
    val kind: String,
    @param:JsonProperty
    val id: String,
    @param:JsonProperty
    val etag: String,
    @param:JsonProperty
    val selflink: String?,
    @param:JsonProperty
    val volumeInfo: VolumeInfo?,
    @param:JsonProperty
    val layerInfo: LayerInfo?,
    @param:JsonProperty
    val saleInfo: SaleInfo?,
    @param:JsonProperty
    val accessInfo: AccessInfo?
)

data class AccessInfo(
    @param:JsonProperty
    val country: Country,
    @param:JsonProperty
    val viewability: String,
    @param:JsonProperty
    val embeddable: Boolean,
    @param:JsonProperty
    val publicDomain: Boolean,
    @param:JsonProperty
    val textToSpeechPermission: String,
    @param:JsonProperty
    val epub: Epub,
    @param:JsonProperty
    val pdf: Pdf,
    @param:JsonProperty
    val webReaderLink: String,
    @param:JsonProperty
    val accessViewStatus: String,
    @param:JsonProperty
    val quoteSharingAllowed: Boolean
)

data class Epub(
    @param:JsonProperty
    val isAvailable: Boolean,
    @param:JsonProperty
    val acsTokenLink: String?
)

data class LayerInfo(
    @param:JsonProperty
    val layers: List<Layer>
)

data class Pdf(
    @param:JsonProperty
    val isAvailable: Boolean
)

data class SaleInfo(
    @param:JsonProperty
    val country: Country,
    @param:JsonProperty
    val saleability: String,
    @param:JsonProperty
    val isEbook: Boolean,
    @param:JsonProperty
    val listPrice: ListPrice?,
    @param:JsonProperty
    val retailPrice: RetailPrice?,
    @param:JsonProperty
    val buyLink: String?,
    @param:JsonProperty
    val offers: List<Offer>?
)

data class Offer(
    @param:JsonProperty
    val finskyOfferType: Int,
    @param:JsonProperty
    val listPrice: ListPrice,
    @param:JsonProperty
    val retailPrice: RetailPrice
)

data class RetailPrice(
    @param:JsonProperty
    val amount: BigDecimal?,
    @param:JsonProperty
    val currency: String?
)

data class ListPrice(
    @param:JsonProperty
    val amount: BigDecimal? = null,
    @param:JsonProperty
    val amountInMicros: BigDecimal? = null,
    @param:JsonProperty
    val currency: String?
)

data class Layer(
    @param:JsonProperty
    val layerId: String,
    @param:JsonProperty
    val volumeAnnotationsVersion: String
)

data class VolumeInfo(
    @param:JsonProperty
    val title: String,
    @param:JsonProperty
    val subtitle: String? = null,
    @param:JsonProperty
    val authors: List<Author>? = null,
    @param:JsonProperty
    val publisher: Publisher,
    @param:JsonProperty
    val publishedDate: String? = null,
    @param:JsonProperty
    val description: String? = null,
    @param:JsonProperty
    val industryIdentifiers: List<IndustryIdentifier>? = null,
    @param:JsonProperty
    val readingModes: ReadingModes,
    @param:JsonProperty
    val pageCount: Long,
    @param:JsonProperty
    val printedPageCount: Long,
    @param:JsonProperty
    val dimensions: Dimensions? = null,
    @param:JsonProperty
    val printType: String,
    @param:JsonProperty
    val categories: List<Category>? = null,
    @param:JsonProperty
    val averageRating: BigDecimal? = null,
    @param:JsonProperty
    val ratingsCount: Int = 0,
    @param:JsonProperty
    val maturityRating: String,
    @param:JsonProperty
    val allowAnonLogging: Boolean,
    @param:JsonProperty
    val contentVersion: String,
    @param:JsonProperty
    val panelizationSummary: PanelizationSummary? = null,
    @param:JsonProperty
    val imageLinks: ImageLinks? = null,
    @param:JsonProperty
    val language: Language? = null,
    @param:JsonProperty
    val previewLink: String,
    @param:JsonProperty
    val infoLink: String,
    @param:JsonProperty
    val canonicalVolumeLink: String
)

data class IndustryIdentifier(
    @param:JsonProperty
    val type: String,
    @param:JsonProperty
    val identifier: String
)

data class ReadingModes(
    @param:JsonProperty
    val text: Boolean,
    @param:JsonProperty
    val image: Boolean
)

data class Dimensions(
    @param:JsonProperty
    val height: String?
)

data class PanelizationSummary(
    @param:JsonProperty
    val containsEpubBubbles: Boolean,
    @param:JsonProperty
    val containsImageBubbles: Boolean
)

data class ImageLinks(
    @param:JsonProperty
    val smallThumbnail: String?,
    @param:JsonProperty
    val thumbnail: String?,
    @param:JsonProperty
    val small: String?,
    @param:JsonProperty
    val medium: String?,
    @param:JsonProperty
    val large: String?
)

enum class Language {
    en, nl, de, fr
}

enum class Country {
    EN, NL, DE, FR
}

data class SearchResult(
    val title: String,
    val author: String,
    val isbn: String,
    val publicationDate: String
)