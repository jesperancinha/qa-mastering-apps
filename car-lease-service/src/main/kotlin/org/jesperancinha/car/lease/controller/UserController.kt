package org.jesperancinha.car.lease.controller

import org.jesperancinha.car.lease.dto.UserDto
import org.jesperancinha.car.lease.services.UserService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createUser(
        @RequestBody userDto: UserDto
    ) {
        userService.saveDto(userDto)
    }
}