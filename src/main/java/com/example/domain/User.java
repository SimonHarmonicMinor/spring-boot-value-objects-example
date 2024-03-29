package com.example.domain;

import com.example.domain.converter.PassportConverter;
import com.example.domain.converter.PhoneNumberConverter;
import com.example.domain.converter.UserNameAttributeConverter;
import com.example.domain.value.Passport;
import com.example.domain.value.PhoneNumber;

import jakarta.persistence.Id;
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
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
public class User {
    @Id
    private UUID id;

    private String name;

    public void changeName(User.Name name) {
        this.name = name.getValue();
    }

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
        user.id = UUID.randomUUID();
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

    @Value
    public static class Name {
        String value;
    }
}
