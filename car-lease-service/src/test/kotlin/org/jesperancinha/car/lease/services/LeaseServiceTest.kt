package org.jesperancinha.car.lease.services;

import org.jesperancinha.car.lease.dto.LeaseDto;
import org.jesperancinha.car.lease.model.Car;
import org.jesperancinha.car.lease.model.Customer;
import org.jesperancinha.car.lease.repository.CarRepository;
import org.jesperancinha.car.lease.repository.CustomerRepository;
import org.jesperancinha.car.lease.repository.LeaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = LeaseService.class)
class LeaseServiceTest {

    @Autowired
    private LeaseService leaseService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private LeaseRepository leaseRepository;

    @BeforeEach
    public void setup() {
        when(customerRepository.getOne(1L))
                .thenReturn(Customer.builder().build());
        when(carRepository.getOne(1L))
                .thenReturn(Car.builder().millage(10000L).netPrice(20000L).build());
        when(leaseRepository.save(any()))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
    }

    @Test
    void testCreateLease() {
        final LeaseDto leaseDto = LeaseDto
                .builder()
                .duration(10000L)
                .carId(1L)
                .customerId(1L)
                .interestRate(2L)
                .build();


        final LeaseDto lease = leaseService.createLease(leaseDto);

        assertThat(lease).isNotNull();
        assertThat(lease.getLeaseRate()).isEqualTo(450.0);
    }
}