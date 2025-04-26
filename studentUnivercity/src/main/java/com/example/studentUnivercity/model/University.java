package com.example.studentUnivercity.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;


@Entity
@Table(name = "universities")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank //not null not blank not even white spaces
    private String universityName;

    private String location;
    private Long establishedYear;
    private String website;
    private String email;
    private String phoneNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UniversityType universityType;

    @OneToMany(mappedBy = "university") // // "university" is the field name in Student entity
    private List<Student> student;
    //don’t create a new join column in University! It's already mapped inside Student!"

    //"In the Student entity, there is a field called university, and that's where the foreign key is stored."
    //So the mappedBy uses the field name from the child entity.

    @OneToMany(mappedBy = "university")
    private List<Course> course;





}
