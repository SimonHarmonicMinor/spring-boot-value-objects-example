package com.example.domain.value;

import com.example.domain.exception.PassportSeriesParsingException;
import com.example.domain.exception.RegionParsingException;
import com.example.util.SelfValidated;

import java.time.OffsetDateTime;
import java.time.Year;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import static java.lang.String.format;

@Value
@EqualsAndHashCode(callSuper = false)
public class PassportSeries extends SelfValidated {
    @NotNull(message = "Passport series region cannot be null")
    Region region;
    @NotNull(message = "Passport series year cannot be null")
    Year year;

    public PassportSeries(Region region, Year year) {
        this.region = region;
        this.year = validateYear(year);
        validateSelf();
    }

    public static PassportSeries parse(String value) {
        if (value == null) {
            throw new PassportSeriesParsingException("Passport number value cannot be null");
        }
        final var trim = value.trim();
        if (trim.length() != 4) {
            throw new PassportSeriesParsingException(
                "Passport number value should have 4 characters but it's: " + trim
            );
        }
        return new PassportSeries(
            Region.parse(value.substring(0, 3)),
            Year.parse("20" + value.substring(3))
        );
    }

    @Override
    public String toString() {
        return format(
            "%02d%02d",
            region.getCode(), year.getValue() % 100
        );
    }

    private static String twoDigits(int value) {
        return value >= 10 ? String.valueOf(value) : "0" + value;
    }

    private static Year validateYear(Year year) {
        final var currentYear = Year.of(OffsetDateTime.now().getYear());
        if (year.isBefore(Year.of(1991)) || year.isAfter(currentYear)) {
            throw new ConstraintViolationException(
                format(
                    "Year must be in range [1991, %s] but it's: %s",
                    currentYear, year
                ),
                null
            );
        }
        return year;
    }

    @RequiredArgsConstructor
    @Getter
    public enum Region {
        KRASNODAR_KRAI(3),
        ALTAI_REPUBLIC(84);
        private final int code;

        public static Region parse(String value) {
            final var code = Integer.parseInt(value);
            for (Region region : Region.values()) {
                if (region.getCode() == code) {
                    return region;
                }
            }
            throw new RegionParsingException(
                "Cannot parse Region from value: " + value
            );
        }
    }
}
