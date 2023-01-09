package org.jesperancinha.car.lease.repository

import org.jesperancinha.car.lease.model.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
internal class UserRepositoryTest {
    @Autowired
    private val userRepository: UserRepository? = null
    @Test
    fun testSaveCar_whenGoodCar_thenSaveCar() {
        val username: Unit = User.builder().username("Joao").build()
        val usernameSave = userRepository!!.save<User>(username)
        assertThat(usernameSave.getId()).isNotNull()
        assertThat(usernameSave.getUsername()).isEqualTo("Joao")
    }
}