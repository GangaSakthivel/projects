package com.example.UserAuthenticationSpring.dto;

import com.example.UserAuthenticationSpring.model.SalaryType;
import com.example.UserAuthenticationSpring.model.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


import java.util.Set;


public class RegistrationRequest {

    @NotBlank
    private String userName;
    @NotNull
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    private String email;
    private String address;
    private Double salary;
    private SalaryType salaryType;
    private Status status;
    @NotBlank
    private String password;
    private String notes;


    private MultipartFile employeePhoto;
    private MultipartFile document;
    private Set<String> roles;//a set of role names, to be passed to the request


    public RegistrationRequest() {
    }

    public RegistrationRequest(String userName, String phoneNumber, String email, String address, Double salary, SalaryType salaryType, Status status, String password, String notes, MultipartFile employeePhoto, MultipartFile document, Set<String> roles) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.salaryType = salaryType;
        this.status = status;
        this.password = password;
        this.notes = notes;
        this.employeePhoto = employeePhoto;
       this.document = document;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public SalaryType getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(SalaryType salaryType) {
        this.salaryType = salaryType;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public MultipartFile getEmployeePhoto() {
        return employeePhoto;
    }

    public void setEmployeePhoto(MultipartFile employeePhoto) {
        this.employeePhoto = employeePhoto;
    }

    public MultipartFile getDocument() {
        return document;
    }

    public void setDocument(MultipartFile document) {
        this.document = document;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }


}