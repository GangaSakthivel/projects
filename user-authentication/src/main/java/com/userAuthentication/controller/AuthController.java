package com.userAuthentication.controller;


import com.userAuthentication.config.JwtUtils;
import com.userAuthentication.dto.BaseResponse;
import com.userAuthentication.dto.UserRequestDTO;
import com.userAuthentication.repository.RoleRepository;
import com.userAuthentication.repository.UserRepository;
import com.userAuthentication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;

        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "register", consumes = {"multipart/form-data"})
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<BaseResponse<String>> userRegistration(@Valid @ModelAttribute UserRequestDTO requestDTO) {
        try {
            String registration = String.valueOf(userService.registerUser(requestDTO));
            BaseResponse<String> response = new BaseResponse<>(true, registration, null);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            String errorMessage = "User registration failed: " + e.getMessage();
            BaseResponse<String> response = new BaseResponse<>(false, null, errorMessage);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }




}


