package org.jesperancinha.car.lease.services

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions
import org.jesperancinha.car.lease.dto.LeaseDto
import org.jesperancinha.car.lease.dao.Car
import org.jesperancinha.car.lease.dao.Customer
import org.jesperancinha.car.lease.dao.CarRepository
import org.jesperancinha.car.lease.dao.CustomerRepository
import org.jesperancinha.car.lease.dao.LeaseRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.invocation.InvocationOnMock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [LeaseService::class])
internal class LeaseServiceTest @Autowired constructor(
    private val leaseService: LeaseService
) {

    @MockBean
    private val customerRepository: CustomerRepository? = null

    @MockBean
    private val carRepository: CarRepository? = null

    @MockBean
    private val leaseRepository: LeaseRepository? = null

    @BeforeEach
    fun setup() {
        Mockito.`when`(customerRepository!!.getOne(1L))
            .thenReturn(Customer())
        Mockito.`when`(carRepository!!.getOne(1L))
            .thenReturn(Car(millage = 10000L, netPrice = 20000L))
        Mockito.`when`<Any>(leaseRepository!!.save(ArgumentMatchers.any()))
            .thenAnswer { invocationOnMock: InvocationOnMock -> invocationOnMock.arguments[0] }
    }

    @Test
    fun testCreateLease() {
        val leaseDto = LeaseDto(
            duration = 10000L,
            carId = 1L,
            customerId = 1L,
            interestRate = 2L
        )
        val lease = leaseService.createLease(leaseDto)
        lease.shouldNotBeNull()
        lease.leaseRate shouldBe 450.0
    }
}