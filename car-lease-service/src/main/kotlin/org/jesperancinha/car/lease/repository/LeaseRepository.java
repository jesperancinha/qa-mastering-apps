package org.jesperancinha.car.lease.repository;

import org.jesperancinha.car.lease.model.Lease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaseRepository extends JpaRepository<Lease, Long> {
}
