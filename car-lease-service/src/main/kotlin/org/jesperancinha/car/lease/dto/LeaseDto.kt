package org.jesperancinha.car.lease.dto

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class LeaseDto {
    private val id: Long? = null
    private val carId: Long? = null
    private val customerId: Long? = null
    private val duration: Long? = null
    private val interestRate: Long? = null
    private val leaseRate: Double? = null
}