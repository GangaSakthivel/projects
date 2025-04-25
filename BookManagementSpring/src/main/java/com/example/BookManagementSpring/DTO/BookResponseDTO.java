package com.example.BookManagementSpring.DTO;

import com.example.BookManagementSpring.model.Book;
import com.example.BookManagementSpring.model.BookStatus;

import java.time.LocalDate;

public class BookResponseDTO {
    private Long id;
    private String title;
    private String isbn;
    private LocalDate publicationDate;
    private String language;
    private Double price;
    private BookStatus status;
   private Long author_id; //// Just the ID, not full author info
    //private AuthorResponseDTO authorResponseDTO;

    public BookResponseDTO() {
    }

    public BookResponseDTO(Long id, String title, String isbn, LocalDate publicationDate, String language, Double price, BookStatus status, Long author_id) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.language = language;
        this.price = price;
        this.status = status;
        this.author_id = author_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

}