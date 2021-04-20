package org.jesperancinha.car.lease.services;

import org.jesperancinha.car.lease.converters.LeaseConverter;
import org.jesperancinha.car.lease.dto.LeaseDto;
import org.jesperancinha.car.lease.repository.LeaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaseServiceImpl implements LeaseService {

    private final LeaseRepository leaseRepository;

    public LeaseServiceImpl(LeaseRepository leaseRepository) {
        this.leaseRepository = leaseRepository;
    }

    @Override
    public LeaseDto createLease(LeaseDto leaseDto) {
        double lease = ((((double)leaseDto.getMillage() / 12) * leaseDto.getDuration()) / leaseDto.getCar().getNetPrice())
                + ((((double)leaseDto.getInterestRate() / 100) * leaseDto.getCar().getNetPrice()) / 12);
        leaseDto.setLeaseRate(lease);
        return LeaseConverter.toDto(leaseRepository.save(LeaseConverter.toData(leaseDto)));
    }

    @Override
    public LeaseDto getLeaseById(Long id) {
        return LeaseConverter.toDto(leaseRepository.findById(id).orElse(null));
    }

    @Override
    public LeaseDto updateLease(LeaseDto leaseDto) {
        return LeaseConverter.toDto(leaseRepository.save(LeaseConverter.toData(leaseDto)));
    }

    @Override
    public void deleteCustomerById(Long id) {
        leaseRepository.deleteById(id);
    }

    @Override
    public List<LeaseDto> getAll() {
        return leaseRepository.findAll().stream().map(LeaseConverter::toDto).collect(Collectors.toList());
    }
}
