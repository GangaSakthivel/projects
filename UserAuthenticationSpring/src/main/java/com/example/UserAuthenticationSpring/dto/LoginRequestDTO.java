package com.example.UserAuthenticationSpring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    private Long phoneNumber;
    private String password;

}
