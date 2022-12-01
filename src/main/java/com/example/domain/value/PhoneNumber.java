package com.example.domain.value;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@Value
public class PhoneNumber {
    long value;

    public PhoneNumber(long value) {
        this.value = value;
    }
}
