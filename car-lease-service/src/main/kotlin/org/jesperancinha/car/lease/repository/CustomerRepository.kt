package org.jesperancinha.car.lease.repository

import org.jesperancinha.car.lease.model.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer?, Long?>