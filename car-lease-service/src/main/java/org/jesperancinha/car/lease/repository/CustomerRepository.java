package org.jesperancinha.car.lease.repository;

import org.jesperancinha.car.lease.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
