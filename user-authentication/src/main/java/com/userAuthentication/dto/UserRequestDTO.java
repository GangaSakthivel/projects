package com.userAuthentication.dto;

import com.userAuthentication.enums.RoleName;
import com.userAuthentication.enums.SalaryType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotBlank
    private String userName;

    @NotBlank
    private String phoneNumber;

    private String userEmail;

    private SalaryType salaryType;

    private Double salary;

    private String address;

    private boolean status;

    private MultipartFile photo;

    private MultipartFile document;

    @NotBlank
    private String password;

    private RoleName roleName;

    private String notes;

}
