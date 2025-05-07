package com.example.UserAuthenticationSpring.dto;

import lombok.Data;

@Data
public class LoginDataDTO {
    private Long userid;
    private String token;
}
