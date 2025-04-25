package com.example.BookManagementSpring.DTO;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    //@NotBlank since its a boolean we cannot use @notblank its works only on strings
    private Boolean verificationStatus;

    public AuthorRequestDTO() {
    }

    public AuthorRequestDTO(String fullName, String nationality, String biography, String email, Boolean verificationStatus) {
        this.fullName = fullName;
        this.nationality = nationality;
        this.biography = biography;
        this.email = email;
        this.verificationStatus = verificationStatus;
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
