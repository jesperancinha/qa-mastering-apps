package org.jesperancinha.supermarket.delivery.api

import org.jesperancinha.supermarket.delivery.domain.DeliveryStatus.IN_PROGRESS
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.Instant

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class DeliveryControllerIT @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) {

    @Test
    fun `create delivery IN_PROGRESS returns 201 and body`() {
        val req = DeliveryCreateRequest(
            vehicleId = "AHV-589",
            address = "Example street 15A",
            startedAt = Instant.parse("2023-10-09T12:45:34.678Z"),
            finishedAt = null,
            status = IN_PROGRESS
        )

        val mvcRes = mockMvc.perform(
            post("/deliveries")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
        )
            .andExpect(status().isCreated)
            .andReturn()

        val body: DeliveryResponse = objectMapper.readValue(mvcRes.response.contentAsString)
        body.id.shouldNotBeNull()
        body.vehicleId shouldBe req.vehicleId
        body.address shouldBe req.address
        body.startedAt shouldBe req.startedAt
        body.finishedAt shouldBe req.finishedAt
        body.status shouldBe req.status
    }

    @Test
    fun `validation error when IN_PROGRESS has finishedAt set returns 400`() {
        val req = DeliveryCreateRequest(
            vehicleId = "AHV-123",
            address = "Street 1",
            startedAt = Instant.parse("2023-10-09T12:45:34.678Z"),
            finishedAt = Instant.parse("2023-10-10T12:45:34.678Z"),
            status = IN_PROGRESS
        )

        mockMvc.perform(
            post("/deliveries")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
        )
            .andExpect(status().isBadRequest)
    }
}