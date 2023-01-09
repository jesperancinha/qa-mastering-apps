package org.jesperancinha.car.lease.services

import org.jesperancinha.car.lease.converters.CarConverter
import org.jesperancinha.car.lease.converters.toData
import org.jesperancinha.car.lease.converters.toDto
import org.jesperancinha.car.lease.dto.CarDto
import org.jesperancinha.car.lease.model.Car
import org.jesperancinha.car.lease.repository.CarRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class CarService(private val carRepository: CarRepository) {
    fun createCar(carDto: CarDto): CarDto {
        return carRepository.save(carDto.toData()).toDto()
    }

    fun getCarById(id: Long): CarDto? {
        return CarConverter.toDto(carRepository.findById(id).orElse(null)!!)
    }

    fun updateCar(carDto: CarDto): CarDto? {
        return CarConverter.toDto(carRepository.save(CarConverter.toData(carDto)))
    }

    fun deleteCarById(id: Long) {
        carRepository.deleteById(id)
    }

    fun all(): List<CarDto> = carRepository.findAll().stream().map { obj: Car? -> CarConverter.toDto() }.collect(Collectors.toList())
}