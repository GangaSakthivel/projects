package com.example.BookManagementSpring.DTO;

import com.example.BookManagementSpring.model.BookStatus;

import java.time.LocalDate;

public class BookRequestDTO {

    private String title;
    private String isbn;
    private LocalDate publicationDate;
    private String language;
    private Double price;
    private BookStatus status;
    private Long author_id;

}
