package com.example.domain.exception;

public class PhoneNumberParsingException extends RuntimeException {

  public PhoneNumberParsingException() {
  }

  public PhoneNumberParsingException(String message) {
    super(message);
  }

  public PhoneNumberParsingException(String message, Throwable cause) {
    super(message, cause);
  }

  public PhoneNumberParsingException(Throwable cause) {
    super(cause);
  }

  public PhoneNumberParsingException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
