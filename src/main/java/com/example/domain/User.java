package com.example.domain;

import com.example.domain.converter.PhoneNumberConverter;
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
    private ID id;

    @Column(name = "phone_number")
    @Convert(converter = PhoneNumberConverter.class)
    @NotNull
    private PhoneNumber phoneNumber;

    public static User newUser(PhoneNumber phoneNumber) {
        final var user = new User();
        user.phoneNumber = phoneNumber;
        user.id = new User.ID(UUID.randomUUID());
        return user;
    }

    @Data
    @Setter(PRIVATE)
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor(access = PROTECTED)
    public static class ID implements Serializable {
        @Column(updatable = false)
        @NotNull
        private UUID id;
    }
}
