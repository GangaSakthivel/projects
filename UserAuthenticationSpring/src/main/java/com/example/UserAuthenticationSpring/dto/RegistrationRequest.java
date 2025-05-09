package com.example.UserAuthenticationSpring.dto;

import com.example.UserAuthenticationSpring.model.Status;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.util.Set;


public class RegistrationRequest {

    @NotBlank
    private String userName;
    @NotNull
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    private Double salary;
    private Status status;
    @NotNull
    private String password;
    private Set<String> roles; //a set of role names, to be passed to the request

    public RegistrationRequest() {
    }

    public RegistrationRequest(String userName, String phoneNumber, Double salary, Status status, String password, Set<String> roles) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.status = status;
        this.password = password;
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
