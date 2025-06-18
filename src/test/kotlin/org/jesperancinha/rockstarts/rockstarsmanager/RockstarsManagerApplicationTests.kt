package org.jesperancinha.rockstarts.rockstarsmanager

import org.jesperancinha.rockstarts.rockstarsmanager.containers.AbstractTestContainersIT.DockerPostgresDataInitializer
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(initializers = [DockerPostgresDataInitializer::class])
internal class RockstarsManagerApplicationTests {
    @Test
    fun contextLoads() {
    }
}