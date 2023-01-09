package org.jesperancinha.car.lease.converters;

import org.jesperancinha.car.lease.dto.CustomerDto;
import org.jesperancinha.car.lease.model.Customer;

public class CustomerConverter {
    public static CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .houseNumber(customer.getHouseNumber())
                .street(customer.getStreet())
                .zipCode(customer.getZipCode())
                .place(customer.getPlace())
                .phoneNumber(customer.getPhoneNumber())
                .id(customer.getId())
                .build();
    }

    public static Customer toData(CustomerDto customerDto) {
        return Customer.builder()
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .houseNumber(customerDto.getHouseNumber())
                .street(customerDto.getStreet())
                .zipCode(customerDto.getZipCode())
                .place(customerDto.getPlace())
                .phoneNumber(customerDto.getPhoneNumber())
                .id(customerDto.getId())
                .build();
    }
}
