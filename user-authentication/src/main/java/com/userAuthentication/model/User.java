package com.userAuthentication.model;


import com.userAuthentication.enums.SalaryType;
import com.userAuthentication.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    private Long id;
    private String userName;
    private List<Role> roles;
    private String phoneNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private SalaryType salaryType;
    private Double salary;
    private String address;
    @Enumerated(EnumType.STRING)
    private Status status;
    private byte[] employeePhoto;



}
