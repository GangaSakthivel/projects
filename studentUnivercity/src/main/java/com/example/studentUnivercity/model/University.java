package com.example.studentUnivercity.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "universities")
public class University {

    private Long id;
    private String universityName;
    private String location;
    private Long establishedYear;
    private String website;
    private String email;
    private String phoneNumber;
    private UniversityType universityType;



}
