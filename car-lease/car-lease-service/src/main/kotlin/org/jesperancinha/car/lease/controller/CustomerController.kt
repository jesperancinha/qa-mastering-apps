package org.jesperancinha.car.lease.controller

import org.jesperancinha.car.lease.dto.CustomerDto
import org.jesperancinha.car.lease.services.CustomerService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
class CustomerController(private val customerService: CustomerService) {
    @GetMapping
    fun listCustomers(): List<CustomerDto?>? = customerService.all()

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createCustomer(@RequestBody customerDto: CustomerDto): CustomerDto? = customerService.createCustomer(customerDto)
}