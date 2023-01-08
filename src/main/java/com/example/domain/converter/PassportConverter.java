package com.example.domain.converter;

import com.example.domain.value.Passport;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PassportConverter implements AttributeConverter<Passport, String> {
    @Override
    public String convertToDatabaseColumn(Passport attribute) {
        return attribute.toString();
    }

    @Override
    public Passport convertToEntityAttribute(String dbData) {
        return Passport.parse(dbData);
    }
}
