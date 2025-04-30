package com.example.studentUnivercity.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public class CourseRequestDTO {

    @NotBlank
    private String name;
    private String description;
    @NotNull
    private Integer credits;
    @NotBlank
    private String semester;
    private LocalDate startDate;
    private LocalDate endDate;
    private String duration;
    private String level;
    private Long university_id;

    public CourseRequestDTO() {
    }

    public CourseRequestDTO(Long id, String name, String description, Integer credits, String semester, LocalDate startDate, LocalDate endDate, String duration, String level, Long university_id) {
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.semester = semester;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.level = level;
        this.university_id = university_id;
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

    public Long getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(Long university_id) {
        this.university_id = university_id;
    }
}
