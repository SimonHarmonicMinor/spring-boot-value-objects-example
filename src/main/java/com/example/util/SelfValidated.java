package com.example.util;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class SelfValidated {
    private static final Validator VALIDATOR =
        Validation.buildDefaultValidatorFactory().getValidator();

    public final void validateSelf() {
        final var violations = VALIDATOR.validate(this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
