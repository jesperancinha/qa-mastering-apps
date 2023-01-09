package org.jesperancinha.car.lease.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
