package org.jesperancinha.car.lease.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.jesperancinha.car.lease.dto.CustomerDto
import org.jesperancinha.car.lease.services.CustomerService
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

@WebMvcTest(CustomerController::class)
internal class CustomerControllerTest {
    @Autowired
    private val mockMvc: MockMvc? = null

    @MockBean
    lateinit var customerService: CustomerService
    private val customerDto: CustomerDto = CustomerDto(
        id=10000,
        name ="Joao",
    )
    private val objectMapper = ObjectMapper()
    @BeforeEach
    fun setUp() {
        Mockito.`when`(customerService.all())
            .thenReturn(listOf(customerDto))
        Mockito.`when`(customerService.createCustomer(customerDto)).thenReturn(customerDto)
    }

    @Test
    @WithMockUser(username = "Joao", roles = ["USER"])
    @Throws(Exception::class)
    fun testListCustomers_Cars_whenCalled_thenGetFullList() {
        mockMvc!!.perform(MockMvcRequestBuilders.get("/customers"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(List.of(customerDto))))
    }

    @Test
    @WithMockUser(username = "Joao", roles = ["USER"])
    @Throws(Exception::class)
    fun testCreateCustomer_whenCalled_thenCreateCar() {
        mockMvc!!.perform(
            MockMvcRequestBuilders.post("/customers")
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(customerDto))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}