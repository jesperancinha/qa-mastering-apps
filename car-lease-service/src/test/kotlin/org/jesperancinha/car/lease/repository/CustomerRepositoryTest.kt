package org.jesperancinha.car.lease.repository

import org.jesperancinha.car.lease.model.Customer
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
internal class CustomerRepositoryTest {
    @Autowired
    private val customerRepository: CustomerRepository? = null
    @Test
    fun testSaveCar_whenGoodCar_thenSaveCar() {
        val customer: Unit = Customer.builder().name("customer").build()
        val customerSave = customerRepository!!.save<Customer>(customer)
        assertThat(customerSave.getId()).isNotNull()
        assertThat(customerSave.getName()).isEqualTo("customer")
    }
}