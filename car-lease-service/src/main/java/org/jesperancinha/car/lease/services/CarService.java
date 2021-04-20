package org.jesperancinha.car.lease.services;

import org.jesperancinha.car.lease.dto.CarDto;
import org.jesperancinha.car.lease.model.Car;

import java.util.List;

public interface CarService {

    CarDto createCar(CarDto carDto);

    CarDto getCarById(Long id);

    CarDto updateCar(CarDto carDto);

    void deleteCarById(Long id);

    List<CarDto> getAll();
}
