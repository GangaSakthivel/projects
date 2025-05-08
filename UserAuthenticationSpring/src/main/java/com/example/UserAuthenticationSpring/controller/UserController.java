package com.example.UserAuthenticationSpring.controller;

import com.example.UserAuthenticationSpring.dto.ApiResponse;
import com.example.UserAuthenticationSpring.dto.LoginRequestDTO;
import com.example.UserAuthenticationSpring.dto.LoginResponseDTO;
import com.example.UserAuthenticationSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ApiResponse<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = userService.login(request);
        return new ApiResponse<>(200, "Success", response);
    }
}
