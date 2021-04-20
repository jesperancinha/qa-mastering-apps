package org.jesperancinha.car.lease.services;

import org.jesperancinha.car.lease.dto.CustomerDto;
import org.jesperancinha.car.lease.model.Customer;

public interface CustomerService {

    CustomerDto createCustomer(Customer Customer);

    CustomerDto getCustomerById(Long id);

    CustomerDto updateCustomer(Customer Customer);

    void deleteCustomerById(Long id);
}
