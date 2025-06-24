package org.jesperancinha.qa.lease.services

import org.jesperancinha.qa.lease.converters.toData
import org.jesperancinha.qa.lease.converters.toDto
import org.jesperancinha.qa.lease.dao.Customer
import org.jesperancinha.qa.lease.dao.CustomerRepository
import org.jesperancinha.qa.lease.dto.CustomerDto
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: CustomerRepository) {
    fun createCustomer(customerDto: CustomerDto): CustomerDto? = customerRepository.save(customerDto.toData()).toDto()
    fun getCustomerById(id: Long?): CustomerDto? = null
    fun updateCustomer(customerDto: CustomerDto): CustomerDto? = customerRepository.save(customerDto.toData()).toDto()
    fun deleteCustomerById(id: Long) = customerRepository.deleteById(id)
    fun all(): List<CustomerDto> = customerRepository.findAll().filterNotNull().map { obj: Customer -> obj.toDto() }
}