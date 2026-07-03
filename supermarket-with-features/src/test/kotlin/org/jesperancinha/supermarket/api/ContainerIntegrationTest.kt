package org.jesperancinha.supermarket.api

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.ComposeContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Testcontainers
import java.io.File


private const val POSTGRES_SERVICE = "postgres"

@Testcontainers
@SpringBootTest
class ContainerIntegrationTest {

    companion object {
        val container: ComposeContainer by lazy {
            ComposeContainer(
                File("./docker-compose.override.yml")
            ).withExposedService(POSTGRES_SERVICE, 5432, Wait.forHealthcheck())
        }

        @JvmStatic
        @DynamicPropertySource
        fun databaseProperties(registry: DynamicPropertyRegistry) {
            container.start()
            val postgresServiceHost = container.getServiceHost(POSTGRES_SERVICE, 5432)
            val postgresServicePort = container.getServicePort(POSTGRES_SERVICE, 5432)
            registry.add(
                "spring.datasource.url",
                { "jdbc:postgresql://$postgresServiceHost:$postgresServicePort/deliveries" })
            registry.add("spring.datasource.username", { "postgres" })
            registry.add("spring.datasource.password", { "admin" })
        }
    }
}
