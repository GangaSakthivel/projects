package com.userAuthentication.controller;


import com.userAuthentication.dto.BaseResponseDTO;
import com.userAuthentication.dto.UserRequestDTO;
import com.userAuthentication.dto.UserResponseDTO;
import com.userAuthentication.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/addUser", consumes = {"multipart/form-data"})
    public ResponseEntity<BaseResponseDTO<UserResponseDTO>> createUser(@Valid @ModelAttribute UserRequestDTO requestDTO, HttpServletRequest servletRequest) {
        try {
            UserResponseDTO createdUser = userService.createUser(requestDTO, servletRequest);
            BaseResponseDTO<UserResponseDTO> response = new BaseResponseDTO<>(true, "User created successfully", createdUser);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            String errorMessage = "Failed to create user: " + e.getMessage();
            BaseResponseDTO<UserResponseDTO> errorResponse = new BaseResponseDTO<>(false, errorMessage, null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
