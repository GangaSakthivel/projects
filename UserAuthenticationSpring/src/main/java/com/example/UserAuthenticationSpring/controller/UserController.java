package com.example.UserAuthenticationSpring.controller;

import com.example.UserAuthenticationSpring.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${role.user:ROLE_USER}")
    private String roleUser;

    @GetMapping("/protected/data")
    public ResponseEntity<String> extractAuthorization(@RequestHeader("Authorization") String token) {
        String jwtToken = null;

        // Check if the token exists and starts with "Bearer "
        if (token != null && token.startsWith("Bearer ")) {
            jwtToken = token.substring(7); // Remove "Bearer " prefix
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Authorization header missing or malformed.");
        }

        try {
            // Extract phone number from JWT token
            String phoneNumber = jwtUtil.extractPhoneNumber(jwtToken);
            Set<String> roles = jwtUtil.extractRoles(jwtToken);

            // Check if user has the USER role
            if (roles.contains(roleUser)) {
                return ResponseEntity.ok("Welcome " + phoneNumber + ", here is the user-specific data.");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied: You don't have the necessary role.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token validation failed: " + e.getMessage());
        }
    }
}
