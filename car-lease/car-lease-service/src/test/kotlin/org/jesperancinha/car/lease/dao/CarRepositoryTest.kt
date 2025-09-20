package org.jesperancinha.car.lease.dao

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
internal class CarRepositoryTest @Autowired constructor(
    val carRepository: CarRepository
) {

    @Test
    fun `should save a car`() {
        val car = Car(make = "Renault", millage = 0L, netPrice = 0L)
        val carSave = carRepository.save(car)
        carSave.id.shouldNotBeNull()
        carSave.make shouldBe "Renault"
    }
}