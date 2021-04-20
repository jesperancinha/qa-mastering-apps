package org.jesperancinha.car.lease.services;

import org.jesperancinha.car.lease.dto.CarDto;
import org.jesperancinha.car.lease.model.Car;

public interface CarService {

    CarDto createCar(Car car);

    CarDto getCarById(Long id);

    CarDto updateCar(Car car);

    void deleteCarById(Long id);

}
