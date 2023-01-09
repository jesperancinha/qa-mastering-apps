package org.jesperancinha.car.lease.services;

import org.jesperancinha.car.lease.converters.LeaseConverter;
import org.jesperancinha.car.lease.dto.LeaseDto;
import org.jesperancinha.car.lease.model.Car;
import org.jesperancinha.car.lease.model.Customer;
import org.jesperancinha.car.lease.model.Lease;
import org.jesperancinha.car.lease.repository.CarRepository;
import org.jesperancinha.car.lease.repository.CustomerRepository;
import org.jesperancinha.car.lease.repository.LeaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaseService {

    private final LeaseRepository leaseRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public LeaseService(LeaseRepository leaseRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.leaseRepository = leaseRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    public LeaseDto createLease(LeaseDto leaseDto) {
        final Car car = carRepository.getOne(leaseDto.getCarId());
        final Customer customer = customerRepository.getOne(leaseDto.getCustomerId());
        double lease = ((((double) car.getMillage() / 12) * leaseDto.getDuration()) / car.getNetPrice())
                + ((((double) leaseDto.getInterestRate() / 100) * car.getNetPrice()) / 12);
        leaseDto.setLeaseRate(lease);
        final Lease leaseObject = LeaseConverter.toData(leaseDto);
        leaseObject.setCar(car);
        leaseObject.setCustomer(customer);
        return LeaseConverter.toDto(leaseRepository.save(leaseObject));
    }

    public LeaseDto getLeaseById(Long id) {
        return LeaseConverter.toDto(leaseRepository.findById(id).orElse(null));
    }

    public LeaseDto updateLease(LeaseDto leaseDto) {
        return LeaseConverter.toDto(leaseRepository.save(LeaseConverter.toData(leaseDto)));
    }

    public void deleteCustomerById(Long id) {
        leaseRepository.deleteById(id);
    }

    public List<LeaseDto> getAll() {
        return leaseRepository.findAll().stream().map(LeaseConverter::toDto).collect(Collectors.toList());
    }
}
