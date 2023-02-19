package com.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig {
    private final List<SerdeProvider<?>> serdeProviders;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
            .registerModule(customSerDeModule());
    }

    public com.fasterxml.jackson.databind.Module customSerDeModule() {
        final var module = new SimpleModule("Custom SerDe module");
        for (SerdeProvider provider : serdeProviders) {
            module.addSerializer(provider.getType(), provider.getJsonSerializer());
            module.addDeserializer(provider.getType(), provider.getJsonDeserializer());
        }
        return module;
    }
}
