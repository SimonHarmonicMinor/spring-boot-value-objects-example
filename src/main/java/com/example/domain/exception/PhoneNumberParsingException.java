package com.example.domain.exception;

public class PhoneNumberParsingException extends RuntimeException {
    public PhoneNumberParsingException(String message) {
        super(message);
    }

    public PhoneNumberParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
