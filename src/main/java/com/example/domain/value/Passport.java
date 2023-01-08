package com.example.domain.value;

import com.example.domain.exception.PassportParsingException;
import com.example.util.SelfValidated;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class Passport extends SelfValidated {
    @NotNull(message = "Passport series cannot be null")
    PassportSeries series;
    @NotNull(message = "Passport number cannot be null")
    PassportNumber number;

    public Passport(PassportSeries series, PassportNumber number) {
        this.series = series;
        this.number = number;
        validateSelf();
    }

    @Override
    public String toString() {
        return series.toString() + number.toString();
    }

    public static Passport parse(String value) {
        if (value == null) {
            throw new PassportParsingException(
                "Passport value cannot be null"
            );
        }
        final var trim = value.trim();
        if (trim.length() != 10) {
            throw new PassportParsingException(
                "Invalid passport value. Should contain 10 characters but it is: " + trim
            );
        }
        return new Passport(
            PassportSeries.parse(value.substring(0, 5)),
            PassportNumber.parse(value.substring(5))
        );
    }
}
