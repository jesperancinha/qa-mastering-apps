package org.jesperancinha.car.lease.services;

import org.jesperancinha.car.lease.dto.CustomerDto;
import org.jesperancinha.car.lease.model.Customer;

import java.util.List;

public interface CustomerService {

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto getCustomerById(Long id);

    CustomerDto updateCustomer(CustomerDto customerDto);

    void deleteCustomerById(Long id);

    List<CustomerDto> getAll();
}
