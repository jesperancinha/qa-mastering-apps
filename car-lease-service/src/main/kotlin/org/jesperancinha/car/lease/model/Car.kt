package org.jesperancinha.car.lease.model

import jakarta.persistence.*


@Entity
@Table
class Car(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Column
    val make: String? = null,

    @Column
    val model: String? = null,

    @Column
    val version: String? = null,

    @Column
    val numberDoors: Long? = null,

    @Column
    val co2Emission: Long? = null,

    @Column
    val grossPrice: Long? = null,

    @Column
    val netPrice: Long? = null,

    @Column
    val millage: Long? = null
)