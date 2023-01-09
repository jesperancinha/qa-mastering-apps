package org.jesperancinha.car.lease.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.jesperancinha.car.lease.dto.CarDto
import org.jesperancinha.car.lease.services.CarService
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

@WebMvcTest(CarController::class)
internal class CarControllerTest {
    @Autowired
    private val mockMvc: MockMvc? = null

    @MockBean
    lateinit var carService: CarService

    private val carDto: CarDto = CarDto(
        make= "Fiat",
        model = "Panda",
        version = "1234",
        millage = 1000L,
        netPrice = 0L
    )
    private val objectMapper = ObjectMapper()
    @BeforeEach
    fun setUp() {
        Mockito.`when`(carService.all())
            .thenReturn(List.of(carDto))
        Mockito.`when`(carService.createCar(carDto)).thenReturn(carDto)
    }

    @Test
    @WithMockUser(username = "Joao", roles = ["USER"])
    @Throws(Exception::class)
    fun testListCars_whenCalled_thenGetFullList() {
        mockMvc!!.perform(MockMvcRequestBuilders.get("/cars"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(List.of(carDto))))
    }

    @Test
    @WithMockUser(username = "Joao", roles = ["USER"])
    @Throws(Exception::class)
    fun testCreateCar_whenCalled_thenCreateCar() {
        mockMvc!!.perform(
            MockMvcRequestBuilders.post("/cars")
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(carDto))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(carDto)))
    }
}