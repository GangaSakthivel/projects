package com.example.studentUnivercity.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate DOB;
    private String email;
    private Gender gender;


}
