package com.example.UserAuthenticationSpring.controller;

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
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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


    //user registration
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest registrationRequest) {
        if (userRepository.findByUserName(registrationRequest.getUserName()).isPresent()) {
            return ResponseEntity.badRequest().body("User name is already exists.");
        }

        User newUser = new User();
        newUser.setUserName(registrationRequest.getUserName());
        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());
        newUser.setPassword(encodedPassword);


        //convert role names to entity and assign to user
        Set<Role> roles = new HashSet<>();
        for (String roleName : registrationRequest.getRoles()) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
            roles.add(role);
        }

        newUser.setRoles(roles);
        userRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully.");
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegistrationRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication); // Set the authentication in the context

            String token = util.generateToken(loginRequest.getUserName());
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials"); // Handle authentication failure
        }
    }




}
