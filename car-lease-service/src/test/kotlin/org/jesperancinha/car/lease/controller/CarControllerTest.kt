package org.jesperancinha.car.lease.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jesperancinha.car.lease.dto.CarDto;
import org.jesperancinha.car.lease.services.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    private final CarDto carDto = CarDto.builder()
            .make("Fiat")
            .model("Panda")
            .version("1234")
            .millage(1000L)
            .build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        when(carService.getAll())
                .thenReturn(List.of(carDto));
        when(carService.createCar(carDto)).thenReturn(carDto);
    }

    @Test
    @WithMockUser(username = "Joao",
            roles = "USER")
    public void testListCars_whenCalled_thenGetFullList() throws Exception {
        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(carDto))));
    }

    @Test
    @WithMockUser(username = "Joao",
            roles = "USER")
    public void testCreateCar_whenCalled_thenCreateCar() throws Exception {
        mockMvc.perform(post("/cars")
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(carDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(carDto)));
    }
}