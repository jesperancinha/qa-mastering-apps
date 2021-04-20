package org.jesperancinha.car.lease.controller;

import org.jesperancinha.car.lease.dto.UserDto;
import org.jesperancinha.car.lease.services.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(
            @RequestBody
                    UserDto userDto) {
        userService.saveDto(userDto);
    }
}
