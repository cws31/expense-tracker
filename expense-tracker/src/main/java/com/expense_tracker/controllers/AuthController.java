package com.expense_tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense_tracker.DTOs.LoginRequest;
import com.expense_tracker.DTOs.LoginResponse;
import com.expense_tracker.DTOs.UserRegistrationRequest;
import com.expense_tracker.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {

        return authService.UserRegistration(userRegistrationRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse res = authService.login(loginRequest);
        return ResponseEntity.ok(res);
    }

}
