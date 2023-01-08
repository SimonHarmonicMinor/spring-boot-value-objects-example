package com.example.domain.value;

import jakarta.validation.ConstraintViolationException;
import lombok.Value;

import static java.lang.String.format;

@Value
public class PassportNumber {
    int value;

    public PassportNumber(int value) {
        this.value = validatePassportNumber(value);
    }

    private static int validatePassportNumber(int value) {
        if (!(value >= 0 && value <= 999999)) {
            throw new ConstraintViolationException(
                "Passport number is not valid: " + value,
                null
            );
        }
        return value;
    }

    public static PassportNumber parse(String value) {
        return new PassportNumber(Integer.parseInt(value));
    }

    @Override
    public String toString() {
        return format("%06d", value);
    }
}
