package org.jesperancinha.rockstarts.rockstarsmanager.containers;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractTestContainersIT{

    static PostgreSQLContainer<?> postgreSQLContainer;

    static {
        postgreSQLContainer = new PostgreSQLContainer<>("postgres:14")
                .withUsername("postgres")
                .withPassword("password")
                .withInitScript("schema.sql")
                .withDatabaseName("rockstars")
                .withReuse(true);


        postgreSQLContainer.start();
    }


    public static class DockerPostgresDataInitializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        String jdbcUrl = "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl();
        String username = "spring.datasource.username=" + postgreSQLContainer.getUsername();
        String password = "spring.datasource.password=" + postgreSQLContainer.getPassword();

        @Override
        public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {

            TestPropertySourceUtils
                    .addInlinedPropertiesToEnvironment(applicationContext, jdbcUrl, username, password);
        }
    }

}