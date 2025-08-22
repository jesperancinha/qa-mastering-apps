package org.jesperancinha.supermarket.invoice.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.client.JdkClientHttpRequestFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import java.net.http.HttpClient
import java.util.UUID

data class InvoiceClientResponse(
    val id: UUID,
    val sent: Boolean
)

interface InvoiceClient {
    fun sendInvoice(deliveryId: UUID, address: String): InvoiceClientResponse
}

private data class InvoiceSendRequest(
    val deliveryId: UUID,
    val address: String
)

private data class WiremockInvoiceResponse(
    val id: UUID,
    val sent: Boolean
)

@Component
class NoopInvoiceClient(
    @param:Value("\${invoice.client.base-url:http://localhost:8080}") private val baseUrl: String
) : InvoiceClient {

    val httpClient: HttpClient = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_1_1)
        .build()

    private val client: RestClient = RestClient.builder()
        .baseUrl(baseUrl)
        .requestFactory(JdkClientHttpRequestFactory(httpClient))
        .build()

    override fun sendInvoice(deliveryId: UUID, address: String): InvoiceClientResponse {
        val response = client
            .post()
            .uri("/invoices")
            .body(InvoiceSendRequest(deliveryId = deliveryId, address = address))
            .retrieve()
            .body(WiremockInvoiceResponse::class.java)
            ?: throw IllegalStateException("Empty response from invoice service")

        return InvoiceClientResponse(id = response.id, sent = response.sent)
    }
}