package org.jesperancinha.car.lease.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jesperancinha.car.lease.dto.UserDto;
import org.jesperancinha.car.lease.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ContextConfiguration(classes = {UserController.class, UserService.class, BCryptPasswordEncoder.class})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final UserDto userDto = UserDto
            .builder().username("joao").password("pass").build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        when(userService.saveDto(userDto)).thenReturn(1L);
    }

    @Test
    public void testCreateUser_whenCalled_thenCreateCar() throws Exception {
        mockMvc.perform(post("/users")
                        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(userService, only()).saveDto(userDto);
    }
}