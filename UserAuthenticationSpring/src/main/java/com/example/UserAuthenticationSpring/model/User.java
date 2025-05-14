package com.example.UserAuthenticationSpring.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String phoneNumber;
    private String email;
    private String address;
    private Double salary;
    @Enumerated(EnumType.STRING)
    private SalaryType salaryType;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String password;
    private String notes;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<>(); //many users can have multiple roles

    @Column(name = "employee_photo", columnDefinition = "BYTEA", nullable = true)
    private byte[] employeePhoto;

    @Column(name = "document", columnDefinition = "BYTEA", nullable = true)
    private byte[] document;

    public User() {
    }

    public User(Long id, String userName, String phoneNumber, String email, String address, Double salary, SalaryType salaryType, Status status, String password, String notes, Set<Role> roles, byte[] employeePhoto, byte[] document) {
        this.id = id;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.salaryType = salaryType;
        this.status = status;
        this.password = password;
        this.notes = notes;
        this.roles = roles;
        this.employeePhoto = employeePhoto;
        this.document = document;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public byte[] getEmployeePhoto() {
        return employeePhoto;
    }

    public void setEmployeePhoto(byte[] employeePhoto) {
        this.employeePhoto = employeePhoto;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    //Employee ID (Auto generated),
    //Employee Name, Phone Number, Salary and Account Status.
}