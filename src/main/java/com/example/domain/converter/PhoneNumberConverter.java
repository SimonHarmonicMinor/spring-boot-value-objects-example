package com.example.domain.converter;

import com.example.domain.value.PhoneNumber;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PhoneNumberConverter implements AttributeConverter<PhoneNumber, Long> {
    @Override
    public Long convertToDatabaseColumn(PhoneNumber attribute) {
        return attribute.getValue();
    }

    @Override
    public PhoneNumber convertToEntityAttribute(Long dbData) {
        return new PhoneNumber(dbData);
    }
}
