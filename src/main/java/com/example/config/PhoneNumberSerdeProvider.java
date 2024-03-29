package com.example.config;

import com.example.domain.value.PhoneNumber;
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

@Component
class PhoneNumberSerdeProvider implements SerdeProvider<PhoneNumber> {
    @Override
    public JsonDeserializer<PhoneNumber> getJsonDeserializer() {
        return new JsonDeserializer<>() {
            @Override
            public PhoneNumber deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                final var value = p.getValueAsString();
                if (value == null) {
                    return null;
                }
                return new PhoneNumber(value);
            }
        };
    }

    @Override
    public JsonSerializer<PhoneNumber> getJsonSerializer() {
        return new JsonSerializer<>() {
            @Override
            public void serialize(PhoneNumber value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                if (value == null) {
                    gen.writeNull();
                } else {
                    gen.writeString(value.getValue());
                }
            }
        };
    }

    @Override
    public Class<PhoneNumber> getType() {
        return PhoneNumber.class;
    }
}
