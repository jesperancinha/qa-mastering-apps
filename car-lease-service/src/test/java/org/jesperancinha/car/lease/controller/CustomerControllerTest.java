package org.jesperancinha.car.lease.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jesperancinha.car.lease.dto.CustomerDto;
import org.jesperancinha.car.lease.services.CustomerServiceImpl;
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

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceImpl customerService;

    private final CustomerDto customerDto = CustomerDto.builder()
            .name("Joao")
            .build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        when(customerService.getAll())
                .thenReturn(List.of(customerDto));
        when(customerService.createCustomer(customerDto)).thenReturn(customerDto);
    }

    @Test
    @WithMockUser(username = "Joao",
            roles = "USER")
    public void testListCustomers_Cars_whenCalled_thenGetFullList() throws Exception {
        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(customerDto))));
    }

    @Test
    @WithMockUser(username = "Joao",
            roles = "USER")
    public void testCreateCustomer_whenCalled_thenCreateCar() throws Exception {
        mockMvc.perform(post("/customers")
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(customerDto)));
    }
}