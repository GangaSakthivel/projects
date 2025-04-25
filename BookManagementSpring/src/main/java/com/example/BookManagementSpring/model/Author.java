package com.example.BookManagementSpring.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String fullName;
    @NotBlank
    private String nationality;
    @NotBlank
    private String biography;
    //@Enumerated(EnumType.STRING)//not needed for boolean types
    private Boolean VerificationStatus;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Book> books;

}
