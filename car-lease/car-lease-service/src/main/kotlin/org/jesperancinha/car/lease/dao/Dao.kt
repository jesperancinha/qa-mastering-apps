package org.jesperancinha.car.lease.dao

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import java.io.Serializable

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
    val netPrice: Long,

    @Column
    val millage: Long
)

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

@Entity
@Table
data class Lease(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @OneToOne
    val car: Car? = null,

    @OneToOne
    val customer: Customer? = null,

    @Column
    val duration: Long,

    @Column
    val interestRate: Long,

    @Column
    val netPrice: Long? = null,

    @Column
    val leaseRate: Double? = null
)

@Table
@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Column
    val username: String? = null,

    @Column
    val password: String? = null,

    @Column
    val firstName: String? = null,

    @Column
    val lastName: String? = null,

    @Column
    val email: String? = null
) : Serializable

interface CarRepository : JpaRepository<Car, Long>
interface CustomerRepository : JpaRepository<Customer, Long>
interface LeaseRepository : JpaRepository<Lease, Long>
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}