package org.jesperancinha.car.lease.services;

import org.jesperancinha.car.lease.converters.CustomerConverter;
import org.jesperancinha.car.lease.dto.CustomerDto;
import org.jesperancinha.car.lease.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        return CustomerConverter.toDto(customerRepository.save(CustomerConverter.toData(customerDto)));
    }

    public CustomerDto getCustomerById(Long id) {
        return null;
    }

    public CustomerDto updateCustomer(CustomerDto customerDto) {
        return CustomerConverter.toDto(customerRepository.save(CustomerConverter.toData(customerDto)));
    }

    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    public List<CustomerDto> getAll() {
        return customerRepository.findAll().stream().map(CustomerConverter::toDto).collect(Collectors.toList());
    }
}
