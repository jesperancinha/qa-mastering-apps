package org.jesperancinha.car.lease.services;

import org.jesperancinha.car.lease.converters.CustomerConverter;
import org.jesperancinha.car.lease.dto.CustomerDto;
import org.jesperancinha.car.lease.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        return CustomerConverter.toDto(customerRepository.save(CustomerConverter.toData(customerDto)));
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return null;
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        return CustomerConverter.toDto(customerRepository.save(CustomerConverter.toData(customerDto)));
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDto> getAll() {
        return customerRepository.findAll().stream().map(CustomerConverter::toDto).collect(Collectors.toList());
    }
}
