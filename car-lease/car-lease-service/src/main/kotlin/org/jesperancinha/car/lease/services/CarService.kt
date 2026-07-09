package org.jesperancinha.car.lease.services

import org.jesperancinha.car.lease.dao.Car
import org.jesperancinha.car.lease.dao.CarRepository
import org.jesperancinha.car.lease.converters.toData
import org.jesperancinha.car.lease.converters.toDto
import org.jesperancinha.car.lease.dto.CarDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CarService(private val carRepository: CarRepository) {
    // Ignore any client-supplied id on create: JPA save() with a non-null id performs
    // a merge/update, so a client-chosen id could silently overwrite another row.
    fun createCar(carDto: CarDto): CarDto = carRepository.save(carDto.copy(id = null).toData()).toDto()

    fun getCarById(id: Long): CarDto? = carRepository.findByIdOrNull(id)?.toDto()

    fun updateCar(carDto: CarDto): CarDto? = carRepository.save(carDto.toData()).toDto()

    fun deleteCarById(id: Long) = carRepository.deleteById(id)

    fun all(): List<CarDto> = carRepository.findAll().filterNotNull().map { obj: Car -> obj.toDto() }
}