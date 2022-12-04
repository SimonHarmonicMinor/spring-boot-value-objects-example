package com.example.config;

import com.example.domain.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

@Component
class UserIdSerdeProvider implements SerdeProvider<User.ID> {
    @Override
    public JsonDeserializer<User.ID> getJsonDeserializer() {
        return new JsonDeserializer<>() {
            @Override
            public User.ID deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                if (p.isNaN()) {
                    return null;
                }
                return new User.ID(UUID.fromString(p.getValueAsString()));
            }
        };
    }

    @Override
    public JsonSerializer<User.ID> getJsonSerializer() {
        return new JsonSerializer<>() {
            @Override
            public void serialize(User.ID value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                if (value == null) {
                    gen.writeNull();
                } else {
                    gen.writeString(value.getId().toString());
                }
            }
        };
    }

    @Override
    public Formatter<User.ID> getTypedFieldFormatter() {
        return new Formatter<>() {
            @Override
            public User.ID parse(String text, Locale locale) {
                return new User.ID(UUID.fromString(text));
            }

            @Override
            public String print(User.ID object, Locale locale) {
                return object.getId().toString();
            }
        };
    }

    @Override
    public Class<User.ID> getType() {
        return User.ID.class;
    }
}
