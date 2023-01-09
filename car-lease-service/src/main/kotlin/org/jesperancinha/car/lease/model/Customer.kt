package org.jesperancinha.car.lease.model

import jakarta.persistence.*

@Entity
@Table
class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Column
    val name: String? = null,

    @Column
    val street: String? = null,

    @Column
    val houseNumber: Long? = null,

    @Column
    val zipCode: String? = null,

    @Column
    val place: String? = null,

    @Column
    val email: String? = null,

    @Column
    val phoneNumber: String? = null
)