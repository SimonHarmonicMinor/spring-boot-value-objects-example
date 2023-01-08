package com.example.domain;

import com.example.domain.value.Passport;
import com.example.domain.value.PhoneNumber;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

import jakarta.validation.constraints.NotNull;

public interface UserRepository extends JpaRepository<User, User.ID> {
    Optional<User> findByPhoneNumber(@NotNull PhoneNumber phoneNumber);

    @Query("""
        SELECT u.passport FROM User u
        WHERE u.id = :id
        """)
    Optional<Passport> findPassportById(User.ID id);
}
