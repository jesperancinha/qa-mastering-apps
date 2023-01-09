package org.jesperancinha.car.lease.services

import org.jesperancinha.car.lease.converters.LeaseConverter
import org.jesperancinha.car.lease.dto.LeaseDto
import org.jesperancinha.car.lease.model.Lease
import org.jesperancinha.car.lease.repository.CarRepository
import org.jesperancinha.car.lease.repository.CustomerRepository
import org.jesperancinha.car.lease.repository.LeaseRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class LeaseService(
    private val leaseRepository: LeaseRepository,
    private val carRepository: CarRepository,
    private val customerRepository: CustomerRepository
) {
    fun createLease(leaseDto: LeaseDto): LeaseDto? {
        val car = carRepository.getOne(leaseDto.carId)
        val customer = customerRepository.getOne(leaseDto.customerId)
        val lease =
            car.millage.toDouble() / 12 * leaseDto.duration / car.netPrice + leaseDto.interestRate.toDouble() / 100 * car.netPrice / 12
        leaseDto.leaseRate = lease
        val leaseObject = LeaseConverter.toData(leaseDto)
        leaseObject.car = car
        leaseObject.customer = customer
        return LeaseConverter.toDto(leaseRepository.save(leaseObject))
    }

    fun getLeaseById(id: Long): LeaseDto? {
        return LeaseConverter.toDto(leaseRepository.findById(id).orElse(null)!!)
    }

    fun updateLease(leaseDto: LeaseDto): LeaseDto? {
        return LeaseConverter.toDto(leaseRepository.save(LeaseConverter.toData(leaseDto)))
    }

    fun deleteCustomerById(id: Long) {
        leaseRepository.deleteById(id)
    }

    val all: List<LeaseDto?>
        get() = leaseRepository.findAll().stream().map { obj: Lease? -> LeaseConverter.toDto() }
            .collect(Collectors.toList())
}