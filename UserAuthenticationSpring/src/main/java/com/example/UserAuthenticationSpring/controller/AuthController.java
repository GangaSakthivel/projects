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



    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> register(
            @ModelAttribute RegistrationRequest registrationRequest,
            @RequestParam(value = "employeePhoto", required = false) MultipartFile employeePhotoFile,
            @RequestParam(value = "document", required = false) MultipartFile documentFile) throws IOException {

        if (userRepository.findByPhoneNumber(registrationRequest.getPhoneNumber()).isPresent()) {
            return ResponseEntity.badRequest().body("Phone number already exists.");
        }

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

        Set<Role> roles = new HashSet<>();
        if (registrationRequest.getRoles() != null) {
            for (String roleName : registrationRequest.getRoles()) {
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
                roles.add(role);
            }
        }
        newUser.setRoles(roles);

        if (employeePhotoFile != null && !employeePhotoFile.isEmpty()) {
            newUser.setEmployeePhoto(employeePhotoFile.getBytes());
        }

        if (documentFile != null && !documentFile.isEmpty()) {
                byte[] documentBytes = documentFile.getBytes();
                newUser.setDocument(documentBytes);
            }


        userRepository.save(newUser);
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