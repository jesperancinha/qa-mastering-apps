package org.jesperancinha.car.lease.converters

import org.jesperancinha.car.lease.dao.Car
import org.jesperancinha.car.lease.dao.Customer
import org.jesperancinha.car.lease.dao.Lease
import org.jesperancinha.car.lease.dao.User
import org.jesperancinha.car.lease.dto.CarDto
import org.jesperancinha.car.lease.dto.CustomerDto
import org.jesperancinha.car.lease.dto.LeaseDto
import org.jesperancinha.car.lease.dto.UserDto


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

fun CustomerDto.toData() = Customer(
    id = id,
    name = name,
    email = email,
    houseNumber = houseNumber,
    street = street,
    zipCode = zipCode,
    place = place,
    phoneNumber = phoneNumber
)

fun LeaseDto.toData() = Lease(
    id = id,
    duration = duration,
    leaseRate = leaseRate,
    interestRate = interestRate
)

fun Lease.toDto() = LeaseDto(
    id = id,
    carId = car?.id,
    customerId = customer?.id,
    duration = duration,
    leaseRate = leaseRate,
    interestRate = interestRate
)

fun UserDto.toData() = User(
    username = username,
    password = password,
    email = email,
    firstName = firstName,
    lastName = lastName
)
