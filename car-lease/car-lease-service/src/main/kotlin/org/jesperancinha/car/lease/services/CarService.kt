package org.jesperancinha.qa.lease.services

import org.jesperancinha.qa.lease.converters.toData
import org.jesperancinha.qa.lease.converters.toDto
import org.jesperancinha.qa.lease.dto.CarDto
import org.jesperancinha.qa.lease.dao.Car
import org.jesperancinha.qa.lease.dao.CarRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CarService(private val carRepository: CarRepository) {
    fun createCar(carDto: CarDto): CarDto = carRepository.save(carDto.toData()).toDto()

    fun getCarById(id: Long): CarDto? = carRepository.findByIdOrNull(id)?.toDto()

    fun updateCar(carDto: CarDto): CarDto? = carRepository.save(carDto.toData()).toDto()

    fun deleteCarById(id: Long) = carRepository.deleteById(id)

    fun all(): List<CarDto> = carRepository.findAll().filterNotNull().map { obj: Car -> obj.toDto() }
}