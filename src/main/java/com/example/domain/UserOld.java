package com.example.domain;

import com.example.domain.converter.PassportConverter;
import com.example.domain.converter.PhoneNumberConverter;
import com.example.domain.value.Passport;
import com.example.domain.value.PhoneNumber;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class UserOld {
    @Id
    private UUID id;
    @Column(name = "phone_number")
    @Convert(converter = PhoneNumberConverter.class)
    private PhoneNumber phoneNumber;
    @Convert(converter = PassportConverter.class)
    private Passport passport;

    public static UserOld newUser(PhoneNumber phoneNumber, Passport passport) {
        final var user = new UserOld();
        user.id = UUID.randomUUID();
        user.phoneNumber = phoneNumber;
        user.passport = passport;
        return user;
    }
}
