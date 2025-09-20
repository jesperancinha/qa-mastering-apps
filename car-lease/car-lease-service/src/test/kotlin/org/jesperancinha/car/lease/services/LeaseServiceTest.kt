package org.jesperancinha.car.lease.services

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.jesperancinha.car.lease.dao.*
import org.jesperancinha.car.lease.dto.LeaseDto
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
import java.util.*

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
        Mockito.`when`(customerRepository!!.findById(1L))
            .thenReturn(Optional.of(Customer()))
        Mockito.`when`(carRepository!!.findById(1L))
            .thenReturn(Optional.of(Car(millage = 10000L, netPrice = 20000L)))
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