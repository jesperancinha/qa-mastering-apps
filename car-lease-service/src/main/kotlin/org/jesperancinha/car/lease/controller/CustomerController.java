package org.jesperancinha.car.lease.controller;

import org.jesperancinha.car.lease.dto.CustomerDto;
import org.jesperancinha.car.lease.services.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDto> listCustomers() {
        return customerService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto createCustomer(@RequestBody
                                              CustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }
}
