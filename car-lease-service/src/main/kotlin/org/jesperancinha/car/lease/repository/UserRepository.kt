package org.jesperancinha.car.lease.repository

import org.jesperancinha.car.lease.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User?, Long?> {
    fun findByUsername(username: String?): User?
}