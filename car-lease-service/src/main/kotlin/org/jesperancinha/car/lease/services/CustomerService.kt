package org.jesperancinha.car.lease.services

import org.jesperancinha.car.lease.converters.CustomerConverter
import org.jesperancinha.car.lease.dto.CustomerDto
import org.jesperancinha.car.lease.model.Customer
import org.jesperancinha.car.lease.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class CustomerService(private val customerRepository: CustomerRepository) {
    fun createCustomer(customerDto: CustomerDto): CustomerDto? {
        return CustomerConverter.toDto(customerRepository.save(CustomerConverter.toData(customerDto)))
    }

    fun getCustomerById(id: Long?): CustomerDto? {
        return null
    }

    fun updateCustomer(customerDto: CustomerDto): CustomerDto? {
        return CustomerConverter.toDto(customerRepository.save(CustomerConverter.toData(customerDto)))
    }

    fun deleteCustomerById(id: Long) {
        customerRepository.deleteById(id)
    }

    val all: List<CustomerDto?>
        get() = customerRepository.findAll().stream().map { obj: Customer? -> CustomerConverter.toDto() }
            .collect(Collectors.toList())
}