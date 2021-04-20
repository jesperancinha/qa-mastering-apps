package org.jesperancinha.car.lease.services;

import org.jesperancinha.car.lease.dto.LeaseDto;

import java.util.List;

public interface LeaseService {
    LeaseDto createLease(LeaseDto leaseDto);

    LeaseDto getLeaseById(Long id);

    LeaseDto updateLease(LeaseDto leaseDto);

    void deleteCustomerById(Long id);

    List<LeaseDto> getAll();
}
