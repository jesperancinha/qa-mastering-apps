package org.jesperancinha.car.lease.converters;

import org.jesperancinha.car.lease.dto.LeaseDto;
import org.jesperancinha.car.lease.model.Lease;

import java.util.Objects;

public class LeaseConverter {
    public static Lease toData(LeaseDto leaseDto) {
        return Lease.builder()
                .id(leaseDto.getId())
                .duration(leaseDto.getDuration())
                .leaseRate(leaseDto.getLeaseRate())
                .interestRate(leaseDto.getInterestRate())
                .build();
    }

    public static LeaseDto toDto(Lease lease) {
        if (Objects.isNull(lease)) {
            return null;
        }
        return LeaseDto.builder()
                .id(lease.getId())
                .carId(lease.getCar().getId())
                .customerId(lease.getCustomer().getId())
                .duration(lease.getDuration())
                .leaseRate(lease.getLeaseRate())
                .interestRate(lease.getInterestRate())
                .build();
    }
}
