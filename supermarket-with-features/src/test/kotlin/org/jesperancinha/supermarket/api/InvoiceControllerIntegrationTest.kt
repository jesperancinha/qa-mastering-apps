package org.jesperancinha.supermarket.invoice.api

import org.jesperancinha.supermarket.delivery.api.DeliveryCreateRequest
import org.jesperancinha.supermarket.delivery.api.DeliveryResponse
import org.jesperancinha.supermarket.delivery.domain.DeliveryStatus
import org.jesperancinha.supermarket.invoice.client.InvoiceClient
import org.jesperancinha.supermarket.invoice.client.InvoiceClientResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.Instant
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
class InvoiceControllerIT @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) {

    @MockkBean
    lateinit var invoiceClient: InvoiceClient

    @Test
    fun `send invoices returns mapping of deliveryId to invoiceId and calls client`() {
        val createReq = DeliveryCreateRequest(
            vehicleId = "AHV-999",
            address = "Example street 15A",
            startedAt = Instant.parse("2023-10-09T12:45:34.678Z"),
            finishedAt = null,
            status = DeliveryStatus.IN_PROGRESS
        )
        val created = mockMvc.perform(
            post("/deliveries")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createReq))
        )
            .andExpect(status().isCreated)
            .andReturn()
            .let { objectMapper.readValue<DeliveryResponse>(it.response.contentAsString) }

        created.id.shouldNotBeNull()

        val expectedInvoiceId = UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
        every { invoiceClient.sendInvoice(created.id, created.address) } returns
                InvoiceClientResponse(id = expectedInvoiceId, sent = true)

        val request = DeliveryInvoiceRequest(deliveryIds = listOf(created.id))

        val mvcRes = mockMvc.perform(
            post("/deliveries/invoice")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isOk)
            .andReturn()

        val results: List<DeliveryInvoiceResult> =
            objectMapper.readValue(mvcRes.response.contentAsString)

        results.size shouldBe 1
        results[0].deliveryId shouldBe created.id
        results[0].invoiceId shouldBe expectedInvoiceId

        verify(exactly = 1) { invoiceClient.sendInvoice(created.id, created.address) }
    }
}