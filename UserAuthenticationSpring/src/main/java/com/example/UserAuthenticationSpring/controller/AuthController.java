package com.example.UserAuthenticationSpring.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import com.example.UserAuthenticationSpring.dto.RegistrationRequest;
import com.example.UserAuthenticationSpring.model.Role;
import com.example.UserAuthenticationSpring.model.User;
import com.example.UserAuthenticationSpring.repository.RoleRepository;
import com.example.UserAuthenticationSpring.repository.UserRepository;
import com.example.UserAuthenticationSpring.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil util;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil util, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.util = util;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/register", consumes = { "multipart/form-data" })
    public ResponseEntity<String> register(
            @ModelAttribute RegistrationRequest registrationRequest,
            @RequestParam(required = false) MultipartFile employeePhotoFile,
            @RequestParam(required = false) MultipartFile documentFile) throws IOException {

        // Check if the phone number already exists in the database
        if (userRepository.findByPhoneNumber(registrationRequest.getPhoneNumber()).isPresent()) {
            return ResponseEntity.badRequest().body("Phone number already exists.");
        }

        // Create a new User object
        User newUser = new User();
        newUser.setUserName(registrationRequest.getUserName());
        newUser.setPhoneNumber(registrationRequest.getPhoneNumber());
        newUser.setEmail(registrationRequest.getEmail());
        newUser.setAddress(registrationRequest.getAddress());
        newUser.setSalary(registrationRequest.getSalary());
        newUser.setSalaryType(registrationRequest.getSalaryType());
        newUser.setStatus(registrationRequest.getStatus());
        newUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        newUser.setNotes(registrationRequest.getNotes());

        // Assign roles to the user
        Set<Role> roles = new HashSet<>();
        if (registrationRequest.getRoles() != null) {
            for (String roleName : registrationRequest.getRoles()) {
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
                roles.add(role);
            }
        }
        newUser.setRoles(roles);

        // Handle file uploads (photo and document)
        if (employeePhotoFile != null && !employeePhotoFile.isEmpty()) {
            try {
                // Convert the uploaded MultipartFile to byte array
                byte[] photoData = employeePhotoFile.getBytes();

                // Store the byte array into the entity (assuming employeePhoto is bytea in the database)
                newUser.setEmployeePhoto(photoData);
            } catch (IOException e) {
                // Handle any exception that occurs during file processing
                throw new RuntimeException("Error while processing the photo", e);
            }
        }

        if (documentFile != null && !documentFile.isEmpty()) {
            try {
                byte[] documentBytes = documentFile.getBytes();
                newUser.setDocument(documentBytes);
            } catch (IOException e) {
                throw new RuntimeException("Error while processing the document", e);
            }
        }

        // Save the new user to the database
        userRepository.save(newUser);

        // Return success message
        return ResponseEntity.ok("User registered successfully.");
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegistrationRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getPhoneNumber(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //String token = util.generateToken(loginRequest.getUserName());
            User user = userRepository.findByPhoneNumber(loginRequest.getPhoneNumber())
                    .orElseThrow(() -> new RuntimeException("Not found"));
            String token = util.generateToken(user);

            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials"); //if authentication failed
        }
    }




}