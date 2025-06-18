package org.jesperancinha.rockstarts.rockstarsmanager.containers

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.support.TestPropertySourceUtils
import org.testcontainers.containers.PostgreSQLContainer

object AbstractTestContainersIT {
    val postgreSQLContainer: PostgreSQLContainer<*> by lazy {
        PostgreSQLContainer("postgres:14")
            .withUsername("postgres")
            .withPassword("password")
            .withInitScript("schema.sql")
            .withDatabaseName("rockstars")
            .withReuse(true).also { it.start() }
     }

    class DockerPostgresDataInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        private var jdbcUrl = "spring.datasource.url=" + postgreSQLContainer.jdbcUrl
        private var username = "spring.datasource.username=" + postgreSQLContainer.username
        private var password = "spring.datasource.password=" + postgreSQLContainer.password
        override fun initialize(applicationContext: ConfigurableApplicationContext) {
            TestPropertySourceUtils
                .addInlinedPropertiesToEnvironment(applicationContext, jdbcUrl, username, password)
        }
    }
}