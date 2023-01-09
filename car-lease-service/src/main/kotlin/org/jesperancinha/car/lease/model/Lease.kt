package org.jesperancinha.car.lease.model

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null

    @OneToOne
    private val car: Car? = null

    @OneToOne
    private val customer: Customer? = null

    @Column
    private val duration: Long? = null

    @Column
    private val interestRate: Long? = null

    @Column
    private val netPrice: Long? = null

    @Column
    private val leaseRate: Double? = null
}