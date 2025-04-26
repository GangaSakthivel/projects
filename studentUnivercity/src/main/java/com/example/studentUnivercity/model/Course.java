package com.example.studentUnivercity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "courses")
public class Course {


    private String name;
    private String description;
    private Integer credits;
    private String semester;
    private LocalDate startDate;
    private LocalDate endDate;
    private String duration;
    private String level;

    @ManyToOne//many courses (one university can have many courses
    @JoinColumn(name = "university_id") //foreign key column in course table
    private University university;
    //create a column called university_id in the course table that references university(id)."


}











