package org.jesperancinha.car.lease.dto

import lombok.Builder
import lombok.Data

@Data
@Builder
class UserDto {
    private val id: Long? = null
    private val username: String? = null
    private val password: String? = null
    private val firstName: String? = null
    private val lastName: String? = null
    private val email: String? = null
}