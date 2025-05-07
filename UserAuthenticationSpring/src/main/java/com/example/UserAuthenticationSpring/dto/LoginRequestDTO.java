package com.example.UserAuthenticationSpring.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private Long loginId;
    private String password;

}
