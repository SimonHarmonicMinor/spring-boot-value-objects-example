package com.example.controller;

import com.example.domain.User;
import com.example.domain.UserRepository;
import com.example.domain.value.OrderName;
import com.example.domain.value.PhoneNumber;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class UserController {
    private final UserRepository userRepository;

    @PostMapping("/api/order/{orderId}")
    void updateOrderName(@PathVariable UUID orderId, @RequestParam OrderName orderName) {

    }

    @GetMapping("/api/user")
    @Transactional
    UserResponse getUserByPhoneNumber(@RequestParam PhoneNumber phoneNumber) {
        final var user = userRepository.findByPhoneNumber(phoneNumber).orElseThrow();
        return new UserResponse(user.getId(), user.getPhoneNumber());
    }

    record UserResponse(UUID id, PhoneNumber phoneNumber) {
    }
}
