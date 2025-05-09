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

    @Value("${role.user:ROLE_USER}")//used to inject a value into a field, method, constructor parameter.
    private String roleUser;

    @GetMapping("/protected/data")
    public ResponseEntity<String> extractAuthorization(@RequestHeader("Authorization") String token) {
        String jwtToken = null;

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
                return ResponseEntity.ok("Authorized " + phoneNumber);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token validation failed: " + e.getMessage());
        }
    }
}
