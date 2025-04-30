package com.example.studentUnivercity.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CourseResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Integer credits;
    private String semester;
    private LocalDate startDate;
    private LocalDate endDate;
    private String duration;
    private String level;
    private String universityName;
    private String universityWebsite;

    public CourseResponseDTO() {
    }

    public CourseResponseDTO(Long id, String name, String description, Integer credits, String semester, LocalDate startDate, LocalDate endDate, String duration, String level, String universityName, String universityWebsite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.semester = semester;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.level = level;
        this.universityName = universityName;
        this.universityWebsite = universityWebsite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityWebsite() {
        return universityWebsite;
    }

    public void setUniversityWebsite(String universityWebsite) {
        this.universityWebsite = universityWebsite;
    }
}
