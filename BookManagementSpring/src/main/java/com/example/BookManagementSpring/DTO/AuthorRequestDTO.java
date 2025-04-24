package com.example.BookManagementSpring.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthorRequestDTO {
    @NotBlank(message = "Full name is mandatory")
    @Size(min = 2, max = 100)
    private String fullName;
    @NotBlank(message = "Nationality is mandatory")
    @Size(max = 50)
    private String nationality;
    @NotBlank(message = "Biography is mandatory")
    @Size(min = 10, max = 2000)
    private String biography;
    //@NotBlank since its a boolean we cannot use @notblank its works only on strings
    private Boolean VerificationStatus;

    public AuthorRequestDTO() {
    }

    public AuthorRequestDTO(String fullName, String nationality, String biography, Boolean verificationStatus) {
        this.fullName = fullName;
        this.nationality = nationality;
        this.biography = biography;
        VerificationStatus = verificationStatus;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Boolean getVerificationStatus() {
        return VerificationStatus;
    }

    public void setVerificationStatus(Boolean verificationStatus) {
        VerificationStatus = verificationStatus;
    }
}
