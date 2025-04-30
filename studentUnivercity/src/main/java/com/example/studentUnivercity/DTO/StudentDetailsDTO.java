package com.example.studentUnivercity.DTO;

import java.util.List;

public class StudentDetailsDTO {
    private String firstName;
    private String lastName;
    private String email;
    private UniversityResponseDTO university;
    private List<CourseResponseDTO> courses;

    public StudentDetailsDTO() {
    }

    public StudentDetailsDTO(String firstName, String lastName, String email, UniversityResponseDTO university, List<CourseResponseDTO> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.university = university;
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

    public UniversityResponseDTO getUniversity() {
        return university;
    }

    public void setUniversity(UniversityResponseDTO university) {
        this.university = university;
    }

    public List<CourseResponseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseResponseDTO> courses) {
        this.courses = courses;
    }
}
