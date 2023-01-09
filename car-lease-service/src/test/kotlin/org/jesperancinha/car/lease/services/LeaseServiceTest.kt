package org.jesperancinha.car.lease.services

import org.assertj.core.api.Assertions
import org.jesperancinha.car.lease.dto.LeaseDto
import org.jesperancinha.car.lease.model.Car
import org.jesperancinha.car.lease.model.Customer
import org.jesperancinha.car.lease.repository.CarRepository
import org.jesperancinha.car.lease.repository.CustomerRepository
import org.jesperancinha.car.lease.repository.LeaseRepository
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
internal class LeaseServiceTest {
    @Autowired
    private val leaseService: LeaseService? = null

    @MockBean
    private val customerRepository: CustomerRepository? = null

    @MockBean
    private val carRepository: CarRepository? = null

    @MockBean
    private val leaseRepository: LeaseRepository? = null
    @BeforeEach
    fun setup() {
        Mockito.`when`(customerRepository!!.getOne(1L))
            .thenReturn(Customer.builder().build())
        Mockito.`when`(carRepository!!.getOne(1L))
            .thenReturn(Car.builder().millage(10000L).netPrice(20000L).build())
        Mockito.`when`<Any>(leaseRepository!!.save(ArgumentMatchers.any()))
            .thenAnswer { invocationOnMock: InvocationOnMock -> invocationOnMock.arguments[0] }
    }

    @Test
    fun testCreateLease() {
        val leaseDto: LeaseDto = LeaseDto
            .builder()
            .duration(10000L)
            .carId(1L)
            .customerId(1L)
            .interestRate(2L)
            .build()
        val lease = leaseService!!.createLease(leaseDto)
        Assertions.assertThat(lease).isNotNull
        assertThat(lease.getLeaseRate()).isEqualTo(450.0)
    }
}