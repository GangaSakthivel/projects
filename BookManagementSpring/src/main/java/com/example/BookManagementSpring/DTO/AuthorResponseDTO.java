package com.example.BookManagementSpring.DTO;

public class AuthorResponseDTO {

    private Long id;                     // ID of the author
    private String fullName;
    private String nationality;
    private String biography;
    private String email;
    private Boolean verificationStatus;

    public AuthorResponseDTO() {
    }

    public AuthorResponseDTO(Long id, String fullName, String nationality, String biography, String email, Boolean verificationStatus) {
        this.id = id;
        this.fullName = fullName;
        this.nationality = nationality;
        this.biography = biography;
        this.email = email;
        this.verificationStatus = verificationStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(Boolean verificationStatus) {
        this.verificationStatus = verificationStatus;
    }
}
