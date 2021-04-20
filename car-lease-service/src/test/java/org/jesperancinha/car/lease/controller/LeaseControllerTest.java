package org.jesperancinha.car.lease.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jesperancinha.car.lease.dto.LeaseDto;
import org.jesperancinha.car.lease.services.LeaseServiceImpl;
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

@WebMvcTest(LeaseController.class)
class LeaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LeaseServiceImpl leaseService;

    private final LeaseDto leaseDto = LeaseDto.builder()
            .duration(1000L)
            .build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        when(leaseService.getAll())
                .thenReturn(List.of(leaseDto));
        when(leaseService.createLease(leaseDto)).thenReturn(leaseDto);
    }

    @Test
    @WithMockUser(username = "Joao",
            roles = "USER")
    public void testListLeases_whenCalled_thenGetFullList() throws Exception {
        mockMvc.perform(get("/leases"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(leaseDto))));
    }

    @Test
    @WithMockUser(username = "Joao",
            roles = "USER")
    public void testCreateLease_whenCalled_thenCreateCar() throws Exception {
        mockMvc.perform(post("/leases")
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(leaseDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(leaseDto)));
    }

}