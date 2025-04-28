package com.example.studentUnivercity.DTO;

import com.example.studentUnivercity.model.UniversityType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UniversityRequestDTO {

    @NotBlank
    @Column(unique = true)
    private String universityName;
    private String location;
    private Long establishedYear;
    private String website;
    @Email
    private String email;
    private String phoneNumber;
    @NotNull
    @Enumerated(EnumType.STRING)
    private UniversityType universityType;

    public UniversityRequestDTO() {
    }

    public UniversityRequestDTO(String universityName, String location, Long establishedYear, String website, String email, String phoneNumber, UniversityType universityType) {
        this.universityName = universityName;
        this.location = location;
        this.establishedYear = establishedYear;
        this.website = website;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.universityType = universityType;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getEstablishedYear() {
        return establishedYear;
    }

    public void setEstablishedYear(Long establishedYear) {
        this.establishedYear = establishedYear;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UniversityType getUniversityType() {
        return universityType;
    }

    public void setUniversityType(UniversityType universityType) {
        this.universityType = universityType;
    }
}
