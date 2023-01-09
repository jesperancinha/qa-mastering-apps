package org.jesperancinha.car.lease.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.jesperancinha.car.lease.dto.UserDto
import org.jesperancinha.car.lease.services.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(UserController::class)
@ContextConfiguration(classes = [UserController::class, UserService::class, BCryptPasswordEncoder::class])
internal class UserControllerTest {
    @Autowired
    private val mockMvc: MockMvc? = null

    @MockBean
    private val userService: UserService? = null
    private val userDto: UserDto = UserDto
        .builder().username("joao").password("pass").build()
    private val objectMapper = ObjectMapper()
    @BeforeEach
    fun setUp() {
        Mockito.`when`(userService!!.saveDto(userDto)).thenReturn(1L)
    }

    @Test
    @Throws(Exception::class)
    fun testCreateUser_whenCalled_thenCreateCar() {
        mockMvc!!.perform(
            MockMvcRequestBuilders.post("/users")
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userDto))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(""))
        Mockito.verify(userService, Mockito.only()).saveDto(userDto)
    }
}