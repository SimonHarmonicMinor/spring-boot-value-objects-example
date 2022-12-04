package com.example.domain.value;

import com.example.domain.exception.PhoneNumberParsingException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhoneNumberTest {
    @ParameterizedTest
    @CsvSource({
        "78005553535,78005553535",
        "88005553535,78005553535",
    })
    void shouldParsePhoneNumbersSuccessfully(long input, long expectedOutput) {
        final var phoneNumber = assertDoesNotThrow(
            () -> new PhoneNumber(input)
        );
        assertEquals(expectedOutput, phoneNumber.getValue());
    }

    @ParameterizedTest
    @ValueSource(longs = {
        0, -1, -56
    })
    void shouldThrowExceptionIfPhoneNumberIsNotValid(long input) {
        assertThrows(
            PhoneNumberParsingException.class,
            () -> new PhoneNumber(input)
        );
    }
}