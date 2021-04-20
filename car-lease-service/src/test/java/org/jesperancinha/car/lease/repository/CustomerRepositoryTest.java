package org.jesperancinha.car.lease.repository;

import org.jesperancinha.car.lease.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testSaveCar_whenGoodCar_thenSaveCar() {
        final var customer = Customer.builder().name("customer").build();
        final var customerSave = customerRepository.save(customer);
        assertThat(customerSave.getId()).isNotNull();
        assertThat(customerSave.getName()).isEqualTo("customer");
    }
}