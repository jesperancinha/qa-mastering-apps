package org.jesperancinha.car.lease.converters;

import org.jesperancinha.car.lease.dto.LeaseDto;
import org.jesperancinha.car.lease.model.Lease;

import java.util.Objects;

public class LeaseConverter {
    public static Lease toData(LeaseDto leaseDto) {
        return Lease.builder()
                .id(leaseDto.getId())
                .car(CarConverter.toData(leaseDto.getCar()))
                .customer(CustomerConverter.toData(leaseDto.getCustomer()))
                .duration(leaseDto.getDuration())
                .leaseRate(leaseDto.getLeaseRate())
                .interestRate(leaseDto.getInterestRate())
                .millage(leaseDto.getMillage())
                .netPrice(leaseDto.getNetPrice())
                .build();
    }

    public static LeaseDto toDto(Lease lease) {
        if(Objects.isNull(lease)){
            return null;
        }
        return LeaseDto.builder()
                .id(lease.getId())
                .car(CarConverter.toDto(lease.getCar()))
                .customer(CustomerConverter.toDto(lease.getCustomer()))
                .duration(lease.getDuration())
                .leaseRate(lease.getLeaseRate())
                .interestRate(lease.getInterestRate())
                .millage(lease.getMillage())
                .netPrice(lease.getNetPrice())
                .build();
    }
}
