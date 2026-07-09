package org.jesperancinha.car.lease.services

import org.jesperancinha.car.lease.dao.Customer
import org.jesperancinha.car.lease.dao.CustomerRepository
import org.jesperancinha.car.lease.converters.toData
import org.jesperancinha.car.lease.converters.toDto
import org.jesperancinha.car.lease.dto.CustomerDto
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: CustomerRepository) {
    // Ignore any client-supplied id on create: JPA save() with a non-null id performs
    // a merge/update, so a client-chosen id could silently overwrite another row.
    fun createCustomer(customerDto: CustomerDto): CustomerDto? =
        customerRepository.save(customerDto.copy(id = null).toData()).toDto()
    fun getCustomerById(id: Long?): CustomerDto? = null
    fun updateCustomer(customerDto: CustomerDto): CustomerDto? = customerRepository.save(customerDto.toData()).toDto()
    fun deleteCustomerById(id: Long) = customerRepository.deleteById(id)
    fun all(): List<CustomerDto> = customerRepository.findAll().filterNotNull().map { obj: Customer -> obj.toDto() }
}