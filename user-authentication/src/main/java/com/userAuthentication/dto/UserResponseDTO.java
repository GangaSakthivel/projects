package com.userAuthentication.dto;

import com.userAuthentication.enums.SalaryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id; // The unique identifier of the created user

    private String userName;

    private String phoneNumber;

    private String userEmail;

    private SalaryType salaryType;

    private Double salary;

    private String address;

    private boolean status;

    private String photoUrl; // URL or path to the user's photo
    private String documentUrl; // URL or path to the user's document

    private String notes;

    
}