package com.example.studentUnivercity.DTO;

import com.example.studentUnivercity.model.Gender;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public class StudentRequestDTO {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private LocalDate enrollmentDate;
    private Gender gender;
    private Long university_id;
    private List<String> courses;

    public StudentRequestDTO() {
    }

    public StudentRequestDTO(String firstName, String lastName, String email, String phoneNumber, LocalDate enrollmentDate, Gender gender, Long university_id, List<String> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.enrollmentDate = enrollmentDate;
        this.gender = gender;
        this.university_id = university_id;
        this.courses = courses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(Long university_id) {
        this.university_id = university_id;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}
