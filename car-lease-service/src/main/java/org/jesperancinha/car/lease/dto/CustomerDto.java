package org.jesperancinha.car.lease.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private Long id;
    private String name;
    private String street;
    private Long houseNumber;
    private String zipCode;
    private String place;
    private String email;
    private String phoneNumber;
}
