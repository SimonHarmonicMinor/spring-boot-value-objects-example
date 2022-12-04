package com.example.domain.value;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

import com.example.domain.exception.PhoneNumberParsingException;

import lombok.Value;

import static com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat.E164;

@Value
public class PhoneNumber {
    private static final PhoneNumberUtil PHONE_NUMBER_UTIL = PhoneNumberUtil.getInstance();

    long value;

    public PhoneNumber(long value) {
        this.value = validateAndNormalizePhoneNumber(value);
    }

    private static long validateAndNormalizePhoneNumber(long value) {
        try {
            if (value <= 0) {
                throw new PhoneNumberParsingException("The phone number must be positive: " + value);
            }
            final var phoneNumber = PHONE_NUMBER_UTIL.parse(String.valueOf(value), "RU");
            final String formattedPhoneNumber = PHONE_NUMBER_UTIL.format(phoneNumber, E164);
            // E164 format returns phone number with + character
            return Long.parseLong(formattedPhoneNumber.substring(1));
        } catch (NumberParseException | NumberFormatException e) {
            throw new PhoneNumberParsingException("The phone number isn't valid: " + value, e);
        }
    }
}
