package org.jesperancinha.supermaket.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.jesperancinha.supermaket.domain.DeliveryStatus
import org.jesperancinha.supermaket.dto.*
import org.jesperancinha.supermaket.service.DeliveryService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.Instant
import java.util.*

@WebMvcTest(DeliveryController::class)
class DeliveryControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var deliveryService: DeliveryService

    @Test
    fun `should create delivery and return 201 status`() {
        // Given
        val now = Instant.now()
        val requestDto = DeliveryRequestDto(
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = now,
            finishedAt = null,
            status = DeliveryStatus.IN_PROGRESS
        )

        val responseDto = DeliveryResponseDto(
            id = UUID.randomUUID(),
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = now,
            finishedAt = null,
            status = DeliveryStatus.IN_PROGRESS
        )

        // When
        every { deliveryService.createDelivery(requestDto) } returns responseDto

        // Then
        mockMvc.perform(
            post("/deliveries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto))
        )
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(responseDto.id.toString()))
            .andExpect(jsonPath("$.vehicleId").value(responseDto.vehicleId))
            .andExpect(jsonPath("$.address").value(responseDto.address))
            .andExpect(jsonPath("$.status").value(responseDto.status.toString()))

        verify(exactly = 1) { deliveryService.createDelivery(requestDto) }
    }

    @Test
    fun `should return 400 when validation fails`() {
        // Given
        val now = Instant.now()
        val requestDto = DeliveryRequestDto(
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = now.plusSeconds(3600), // Future date, will fail @PastOrPresent validation
            finishedAt = null,
            status = DeliveryStatus.IN_PROGRESS
        )

        // Then
        mockMvc.perform(
            post("/deliveries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto))
        )
            .andExpect(status().isBadRequest)

        verify(exactly = 0) { deliveryService.createDelivery(any()) }
    }

    @Test
    fun `should get invoices for deliveries`() {
        // Given
        val deliveryId1 = UUID.randomUUID()
        val deliveryId2 = UUID.randomUUID()
        val invoiceId1 = UUID.randomUUID()
        val invoiceId2 = UUID.randomUUID()

        val request = InvoiceRequestDto(listOf(deliveryId1, deliveryId2))
        val response = listOf(
            InvoiceResponseDto(deliveryId1, invoiceId1),
            InvoiceResponseDto(deliveryId2, invoiceId2)
        )

        // When
        every { deliveryService.getInvoicesForDeliveries(request) } returns response

        // Then
        mockMvc.perform(
            post("/deliveries/invoice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].deliveryId").value(deliveryId1.toString()))
            .andExpect(jsonPath("$[0].invoiceId").value(invoiceId1.toString()))
            .andExpect(jsonPath("$[1].deliveryId").value(deliveryId2.toString()))
            .andExpect(jsonPath("$[1].invoiceId").value(invoiceId2.toString()))

        verify(exactly = 1) { deliveryService.getInvoicesForDeliveries(request) }
    }

    @Test
    fun `should get business summary`() {
        // Given
        val summary = DeliveriesSummaryDto(
            deliveries = 5,
            averageMinutesBetweenDeliveryStart = 30
        )

        // When
        every { deliveryService.getYesterdayDeliveriesSummary() } returns summary

        // Then
        mockMvc.perform(get("/deliveries/business-summary"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.deliveries").value(5))
            .andExpect(jsonPath("$.averageMinutesBetweenDeliveryStart").value(30))

        verify(exactly = 1) { deliveryService.getYesterdayDeliveriesSummary() }
    }

    @Test
    fun `should handle service exceptions`() {
        // Given
        val now = Instant.now()
        val requestDto = DeliveryRequestDto(
            vehicleId = "vehicle1",
            address = "123 Main St",
            startedAt = now,
            finishedAt = now, // This will cause validation to fail in service
            status = DeliveryStatus.IN_PROGRESS
        )

        // When
        every { deliveryService.createDelivery(requestDto) } throws IllegalArgumentException("finishedAt must be null for IN_PROGRESS deliveries")

        // Then
        mockMvc.perform(
            post("/deliveries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto))
        )
            .andExpect(status().isBadRequest)

//        verify(exactly = 1) { deliveryService.createDelivery(requestDto) }
    }
}