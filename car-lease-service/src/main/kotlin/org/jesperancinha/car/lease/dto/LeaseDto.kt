package org.jesperancinha.car.lease.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaseDto {

    private Long id;

    private Long carId;

    private Long customerId;

    private Long duration;

    private Long interestRate;

    private Double leaseRate;
}
