package org.jesperancinha.car.lease.exception

class CarNotFoundException(id: Long) : NoSuchElementException("Car not found with id $id")

class CustomerNotFoundException(id: Long) : NoSuchElementException("Customer not found with id $id")
