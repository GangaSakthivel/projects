package com.example.studentUnivercity.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student {

    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate enrollmentDate;
    private Gender gender;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "university_id") //we have this column called university_id in students which referenced to the id column in university
    private University university;




}
