package com.example.domain;

import com.example.domain.converter.PassportConverter;
import com.example.domain.converter.PhoneNumberConverter;
import com.example.domain.value.Passport;
import com.example.domain.value.PhoneNumber;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "users")
@Getter
public class User {
    @EmbeddedId
    @NotNull
    private ID id;

    @Column(name = "phone_number")
    @Convert(converter = PhoneNumberConverter.class)
    @NotNull
    private PhoneNumber phoneNumber;

    @Convert(converter = PassportConverter.class)
    @NotNull
    private Passport passport;

    public static User newUser(PhoneNumber phoneNumber, Passport passport) {
        final var user = new User();
        user.phoneNumber = phoneNumber;
        user.passport = passport;
        user.id = new User.ID(UUID.randomUUID());
        return user;
    }

    @Data
    @Setter(PRIVATE)
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor(access = PROTECTED)
    public static class ID implements Serializable {
        private UUID id;
    }
}
