package org.jesperancinha.qa.lease.controller

import org.jesperancinha.qa.lease.dto.CarDto
import org.jesperancinha.qa.lease.services.CarService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("cars")
class CarController(private val carService: CarService) {
    @GetMapping
    fun listCars(): List<CarDto> = carService.all()

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createCar(@RequestBody carDto: CarDto): CarDto = carService.createCar(carDto)
}