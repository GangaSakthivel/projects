package com.example.UserAuthenticationSpring.dto;


import lombok.Data;

@Data
public class LoginResponseDTO {
    private int status;
    private String message;
    private LoginDataDTO data;
}
