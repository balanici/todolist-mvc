package com.example.todolistmvc.core.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemEntityRepositoryTest {

    @Container
    static PostgreSQLContainer database = new PostgreSQLContainer("postgres:14")
            .withDatabaseName("todolist")
            .withUsername("postgres")
//            .withUsername("postgrestest")
            .withPassword("postgres");
//            .withPassword("postgrestest");

    @DynamicPropertySource
    static void setDatabaseProperties(DynamicPropertyRegistry databaseProperties) {
        databaseProperties.add("spring.datasource.url", database::getJdbcUrl);
//        databaseProperties.add("spring.datasource.username", () -> "postgrestest");
//        databaseProperties.add("spring.datasource.password", () -> "postgrestest");

    }

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
        assertNotNull(entityManager);
        assertNotNull(dataSource);
    }

}