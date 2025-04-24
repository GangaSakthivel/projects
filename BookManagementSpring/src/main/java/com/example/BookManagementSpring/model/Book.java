package com.example.BookManagementSpring.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String isbn;

    @NotNull
    private LocalDate publicationDate;

    @NotBlank
    private String language;

    @NotNull
    private Double price;

    ///@Enumerated(EnumType.STRING) (not needed for boolean type enums)
    private BookStatus status;

    @ManyToOne
    @JoinColumn(name = "author_id")// the Book entity is linked to an Author through author_id.
    private Author author;

    //In the books table, there will be a foreign key column called author_id.
    //It refers to the id column in the authors table.




}
