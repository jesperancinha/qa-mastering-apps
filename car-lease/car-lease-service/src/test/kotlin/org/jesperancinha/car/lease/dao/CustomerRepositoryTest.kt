package org.jesperancinha.car.lease.dao

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
internal class CustomerRepositoryTest @Autowired constructor(
     val customerRepository: CustomerRepository
) {

    @Test
    fun `should save a customer`() {
        val customer = Customer(name = "customer")
        val customerSave = customerRepository.save(customer)
        customerSave.shouldNotBeNull()
        customerSave.id.shouldNotBeNull()
        customerSave.name shouldBe "customer"
    }
}