package org.jesperancinha.car.lease.services

import org.jesperancinha.car.lease.dao.CarRepository
import org.jesperancinha.car.lease.dao.CustomerRepository
import org.jesperancinha.car.lease.dao.Lease
import org.jesperancinha.car.lease.dao.LeaseRepository
import org.jesperancinha.car.lease.converters.toData
import org.jesperancinha.car.lease.converters.toDto
import org.jesperancinha.car.lease.dto.LeaseDto
import org.jesperancinha.car.lease.exception.CarNotFoundException
import org.jesperancinha.car.lease.exception.CustomerNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LeaseService(
    private val leaseRepository: LeaseRepository,
    private val carRepository: CarRepository,
    private val customerRepository: CustomerRepository
) {
    fun createLease(leaseDto: LeaseDto): LeaseDto {
        val carId = requireNotNull(leaseDto.carId)
        val customerId = requireNotNull(leaseDto.customerId)
        val car = carRepository.findByIdOrNull(carId) ?: throw CarNotFoundException(carId)
        val customer = customerRepository.findByIdOrNull(customerId) ?: throw CustomerNotFoundException(customerId)
        val lease =
            car.millage.toDouble() / 12 * leaseDto.duration / car.netPrice + leaseDto.interestRate.toDouble() / 100 * car.netPrice / 12
        val leaseObject = leaseDto.copy(leaseRate = lease).toData().copy(id = null, car = car, customer = customer)
        return leaseRepository.save(leaseObject).toDto()
    }

    fun getLeaseById(id: Long): LeaseDto? = leaseRepository.findByIdOrNull(id)?.toDto()

    fun updateLease(leaseDto: LeaseDto): LeaseDto? = leaseRepository.save(leaseDto.toData()).toDto()

    fun deleteLeaseById(id: Long) = leaseRepository.deleteById(id)

    fun all(): List<LeaseDto> = leaseRepository.findAll().filterNotNull().map { obj: Lease -> obj.toDto() }
}