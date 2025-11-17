package com.expense_tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.expense_tracker.DTOs.LoginRequest;
import com.expense_tracker.DTOs.LoginResponse;
import com.expense_tracker.DTOs.UserRegistrationRequest;
import com.expense_tracker.entities.User;
import com.expense_tracker.repositories.UserRepository;
import com.expense_tracker.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> UserRegistration(UserRegistrationRequest userRegistrationRequest) {

        if (userRepository.existsByEmail(userRegistrationRequest.getEmail())) {
            return ResponseEntity.badRequest().body("email already exists");
        }

        User users = new User();
        users.setFullName(userRegistrationRequest.getFullName());
        users.setEmail(userRegistrationRequest.getEmail());
        users.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        users.setMothlyBudget(userRegistrationRequest.getMonthlyBudget());
        userRepository.save(users);

        return ResponseEntity.ok("Registration successfull");
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        LoginResponse res = new LoginResponse();
        res.setToken(token);
        res.setEmail(user.getEmail());
        res.setFullName(user.getFullName());

        return res;
    }

}
