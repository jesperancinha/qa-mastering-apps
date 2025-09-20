package org.jesperancinha.car.lease.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.jesperancinha.car.lease.dto.LeaseDto
import org.jesperancinha.car.lease.services.LeaseService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.List

@WebMvcTest(LeaseController::class)
internal class LeaseControllerTest {
    @Autowired
    private val mockMvc: MockMvc? = null

    @MockBean
    lateinit var leaseService: LeaseService
    private val leaseDto: LeaseDto = LeaseDto(
        duration =1000L,
        interestRate = 0L
    )
    private val objectMapper = ObjectMapper()
    @BeforeEach
    fun setUp() {
        Mockito.`when`(leaseService.all())
            .thenReturn(List.of(leaseDto))
        Mockito.`when`(leaseService.createLease(leaseDto)).thenReturn(leaseDto)
    }

    @Test
    @WithMockUser(username = "Joao", roles = ["USER"])
    @Throws(Exception::class)
    fun testListLeases_whenCalled_thenGetFullList() {
        mockMvc!!.perform(MockMvcRequestBuilders.get("/leases"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(List.of(leaseDto))))
    }

    @Test
    @WithMockUser(username = "Joao", roles = ["USER"])
    @Throws(Exception::class)
    fun testCreateLease_whenCalled_thenCreateCar() {
        mockMvc!!.perform(
            MockMvcRequestBuilders.post("/leases")
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(leaseDto))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(leaseDto)))
    }
}