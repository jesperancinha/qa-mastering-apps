package org.jesperancinha.car.lease.repository;

import org.jesperancinha.car.lease.model.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void testSaveCar_whenGoodCar_thenSaveCar() {
        final var car = Car.builder().make("Renault").build();
        final Car carSave = carRepository.save(car);
        assertThat(carSave.getId()).isNotNull();
        assertThat(carSave.getMake()).isEqualTo("Renault");
    }

}