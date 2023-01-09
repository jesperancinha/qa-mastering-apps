package org.jesperancinha.car.lease.dao

import org.jesperancinha.car.lease.dao.Car
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
internal class CarRepositoryTest {
    @Autowired
    private val carRepository: CarRepository? = null
    @Test
    fun testSaveCar_whenGoodCar_thenSaveCar() {
        val car: Unit = Car.builder().make("Renault").build()
        val carSave = carRepository!!.save<Car>(car)
        assertThat(carSave.getId()).isNotNull()
        assertThat(carSave.getMake()).isEqualTo("Renault")
    }
}