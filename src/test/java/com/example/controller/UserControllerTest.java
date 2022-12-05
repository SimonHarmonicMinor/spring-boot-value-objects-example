package com.example.controller;

import com.example.domain.value.PhoneNumber;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Testcontainers
class UserControllerTest {
    @Container
    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:13");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
    }

    @Autowired
    private TestRestTemplate rest;

    @Test
    void shouldReturnUserByPhoneNumber() {
        rest.postForEntity("/api/user?phoneNumber={phoneNumber}", null, Void.class, Map.of(
            "phoneNumber", "71234567890"
        ));

        final var response = rest.getForEntity("/api/user?phoneNumber={phoneNumber}", Map.class, Map.of(
            "phoneNumber", "71234567890"
        ));

        assertEquals(
            new PhoneNumber("71234567890"),
            new PhoneNumber((String) response.getBody().get("phoneNumber"))
        );
    }

}