package org.jesperancinha.car.lease.repository;

import org.jesperancinha.car.lease.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
