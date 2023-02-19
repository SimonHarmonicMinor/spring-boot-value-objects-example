package com.example.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.example.testutil.IntegrationSuite;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserRepositoryTest extends IntegrationSuite {

  @Autowired
  private UserRepository userRepository;

  @Test
  void test() {
    assertDoesNotThrow(
        () -> userRepository.findByNameLike("name%")
    );
  }
}