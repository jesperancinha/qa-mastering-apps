package org.jesperancinha.car.lease.controller;

import org.jesperancinha.car.lease.dto.CarDto;
import org.jesperancinha.car.lease.services.CarService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarDto> listCars() {
        return carService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CarDto createCar(@RequestBody CarDto carDto) {
        return carService.createCar(carDto);
    }
}
