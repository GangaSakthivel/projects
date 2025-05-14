package com.userAuthentication.dto;

import com.userAuthentication.enums.SalaryType;
import com.userAuthentication.model.Role;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class UserResponseDTO {

    private Long id;

    private String userName;

    private String phoneNumber;

    private String userEmail;

    private SalaryType salaryType;

    private Double salary;

    private String address;

    private boolean status;

    private String photoUrl;

    private MultipartFile documentUrl;

    private String password;

    private List<Role> roles;

    private String notes;
}
