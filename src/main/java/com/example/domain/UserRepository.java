package com.example.domain;

import com.example.domain.value.PhoneNumber;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import jakarta.validation.constraints.NotNull;

public interface UserRepository extends JpaRepository<User, User.ID> {
    Optional<User> findByPhoneNumber(@NotNull PhoneNumber phoneNumber);
}
