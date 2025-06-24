package org.jesperancinha.qa.lease.converters

import org.jesperancinha.qa.lease.dto.CarDto
import org.jesperancinha.qa.lease.dto.CustomerDto
import org.jesperancinha.qa.lease.dto.LeaseDto
import org.jesperancinha.qa.lease.dto.UserDto
import org.jesperancinha.qa.lease.dao.Car
import org.jesperancinha.qa.lease.dao.Customer
import org.jesperancinha.qa.lease.dao.Lease
import org.jesperancinha.qa.lease.dao.User


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
