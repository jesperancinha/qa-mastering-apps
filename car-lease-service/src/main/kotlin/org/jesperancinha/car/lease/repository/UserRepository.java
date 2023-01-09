package org.jesperancinha.car.lease.repository;

import org.jesperancinha.car.lease.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
