package org.jesperancinha.car.lease.repository;

import org.jesperancinha.car.lease.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveCar_whenGoodCar_thenSaveCar() {
        final var username = User.builder().username("Joao").build();
        final var usernameSave = userRepository.save(username);
        assertThat(usernameSave.getId()).isNotNull();
        assertThat(usernameSave.getUsername()).isEqualTo("Joao");
    }

}