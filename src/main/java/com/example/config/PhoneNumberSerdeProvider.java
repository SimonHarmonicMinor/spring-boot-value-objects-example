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
                if (p.isNaN()) {
                    return null;
                }
                return new PhoneNumber(p.getLongValue());
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
                    gen.writeNumber(value.getValue());
                }
            }
        };
    }

    @Override
    public Formatter<PhoneNumber> getTypedFieldFormatter() {
        return new Formatter<>() {
            @Override
            public PhoneNumber parse(String text, Locale locale) {
                return new PhoneNumber(Long.parseLong(text));
            }

            @Override
            public String print(PhoneNumber object, Locale locale) {
                return String.valueOf(object.getValue());
            }
        };
    }

    @Override
    public Class<PhoneNumber> getType() {
        return PhoneNumber.class;
    }
}
