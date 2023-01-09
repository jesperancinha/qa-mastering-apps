package org.jesperancinha.car.lease.converters

import org.jesperancinha.car.lease.dto.CarDto
import org.jesperancinha.car.lease.dto.CustomerDto
import org.jesperancinha.car.lease.dto.LeaseDto
import org.jesperancinha.car.lease.dto.UserDto
import org.jesperancinha.car.lease.model.Car
import org.jesperancinha.car.lease.model.Customer
import org.jesperancinha.car.lease.model.Lease
import org.jesperancinha.car.lease.model.User
import java.util.*


fun Car.toDto() = CarDto(
    id = id,
    version = version,
    model = model,
    co2Emission = co2Emission,
    make = make,
    netPrice = netPrice,
    numberDoors = numberDoors,
    grossPrice = grossPrice,
    millage = millage
)

fun CarDto.toData() = Car(
    id = id,
    version = version,
    model = model,
    co2Emission = co2Emission,
    make = make,
    netPrice = netPrice,
    numberDoors = numberDoors,
    grossPrice = grossPrice,
    millage = millage
)

fun Customer.toDto() = CustomerDto(
    id = id,
    name = name,
    email = email,
    houseNumber = houseNumber,
    street = street,
    zipCode = zipCode,
    place = place,
    phoneNumber = phoneNumber
)

fun CustomerDto.toDate() = Customer(
    id = id,
    name = name,
    email = email,
    houseNumber = houseNumber,
    street = street,
    zipCode = zipCode,
    place = place,
    phoneNumber = phoneNumber
)

object LeaseConverter {
    fun toData(leaseDto: LeaseDto): Lease {
        return Lease.builder()
            .id(leaseDto.id)
            .duration(leaseDto.duration)
            .leaseRate(leaseDto.leaseRate)
            .interestRate(leaseDto.interestRate)
            .build()
    }

    fun toDto(lease: Lease): LeaseDto? {
        return if (Objects.isNull(lease)) {
            null
        } else LeaseDto.builder()
            .id(lease.id)
            .carId(lease.car.id)
            .customerId(lease.customer.id)
            .duration(lease.duration)
            .leaseRate(lease.leaseRate)
            .interestRate(lease.interestRate)
            .build()
    }
}

object UserConverter {
    fun toUser(userDto: UserDto): User {
        return User.builder()
            .username(userDto.username)
            .password(userDto.password)
            .email(userDto.email)
            .firstName(userDto.firstName)
            .lastName(userDto.lastName)
            .build()
    }
}