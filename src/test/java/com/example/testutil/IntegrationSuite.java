package com.example.testutil;

import com.example.testutil.IntegrationSuite.Initializer;
import java.util.Map;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

@ContextConfiguration(initializers = Initializer.class)
public class IntegrationSuite {

  private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:13");

  static class Initializer implements
      ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext context) {
      Startables.deepStart(POSTGRES).join();

      context.getEnvironment()
          .getPropertySources()
          .addFirst(new MapPropertySource(
              "testcontainers",
              Map.of(
                  "spring.datasource.url", POSTGRES.getJdbcUrl(),
                  "spring.datasource.username", POSTGRES.getUsername(),
                  "spring.datasource.password", POSTGRES.getPassword()
              )
          ));
    }
  }
}
