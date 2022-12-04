package com.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
@SuppressWarnings({"unchecked", "rawtypes"})
public class WebMvcConfig implements WebMvcConfigurer {
    private final List<SerdeProvider<?>> serdeProviders;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        for (SerdeProvider<?> provider : serdeProviders) {
            log.info("Add custom formatter for field type '{}'", provider.getType());
            registry.addFormatterForFieldType(provider.getType(), provider.getTypedFieldFormatter());
        }
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .registerModule(customSerDeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public com.fasterxml.jackson.databind.Module customSerDeModule() {
        final var module = new SimpleModule("Custom SerDe module");
        for (SerdeProvider provider : serdeProviders) {
            log.info("Add custom serde for type '{}'", provider.getType());
            module.addSerializer(provider.getType(), provider.getJsonSerializer());
            module.addDeserializer(provider.getType(), provider.getJsonDeserializer());
        }
        return module;
    }
}
