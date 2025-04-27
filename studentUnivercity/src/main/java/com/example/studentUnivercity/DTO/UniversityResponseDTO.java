package com.example.studentUnivercity.DTO;

import com.example.studentUnivercity.model.UniversityType;

public class UniversityResponseDTO {
    private Long id;
    private String universityName;
    private String location;
    private Long establishedYear;
    private String website;
    private String email;
    private String phoneNumber;
    private UniversityType universityType;

    public UniversityResponseDTO() {
    }

    public UniversityResponseDTO(Long id, String universityName, String location, Long establishedYear, String website, String email, String phoneNumber, UniversityType universityType) {
        this.id = id;
        this.universityName = universityName;
        this.location = location;
        this.establishedYear = establishedYear;
        this.website = website;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.universityType = universityType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
