package com.example.controller;

import com.example.domain.User;
import com.example.domain.UserRepository;
import com.example.domain.value.PhoneNumber;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class UserController {
    private final UserRepository userRepository;

    @PostMapping("/api/user")
    @Transactional
    void createUser(@RequestParam PhoneNumber phoneNumber) {
        userRepository.save(User.newUser(phoneNumber));
    }

    @GetMapping("/api/user")
    @Transactional
    UserResponse getUserByPhoneNumber(@RequestParam PhoneNumber phoneNumber) {
        final var user = userRepository.findByPhoneNumber(phoneNumber).orElseThrow();
        return new UserResponse(user.getId(), user.getPhoneNumber());
    }

    record UserResponse(User.ID id, PhoneNumber phoneNumber) {
    }
}
