package org.jesperancinha.supermarket.summary.api

import org.jesperancinha.supermarket.delivery.api.DeliveryCreateRequest
import org.jesperancinha.supermarket.delivery.domain.DeliveryStatus
import org.jesperancinha.supermarket.delivery.domain.DeliveryStatus.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.MediaType.*
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode
import org.springframework.test.annotation.DirtiesContext.ClassMode.*
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
class SummaryControllerIT @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) {
    @Test
    fun `business summary returns count and average minutes between delivery starts for yesterday`() {
        val ams = ZoneId.of("Europe/Amsterdam")
        val yesterday = LocalDate.now(ams).minusDays(1)

        fun instantAt(hour: Int, minute: Int = 0) =
            yesterday.atTime(LocalTime.of(hour, minute)).atZone(ams).toInstant()

        listOf(1 to "AHV-001", 3 to "AHV-002", 9 to "AHV-003").forEach { (hour, vehicle) ->
            val req = DeliveryCreateRequest(
                vehicleId = vehicle,
                address = "Amsterdam Center $hour",
                startedAt = instantAt(hour),
                finishedAt = null,
                status = IN_PROGRESS
            )
            mockMvc.perform(
                post("/deliveries")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(req))
            ).andExpect(status().isCreated)
        }

        val mvcRes = mockMvc.perform(get("/deliveries/business-summary"))
            .andExpect(status().isOk)
            .andReturn()

        val summary: BusinessSummaryResponse =
            objectMapper.readValue(mvcRes.response.contentAsString)

        summary.deliveries shouldBe 3
        summary.averageMinutesBetweenDeliveryStart shouldBe 240
    }
}