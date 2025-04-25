package com.example.BookManagementSpring.DTO;

import com.example.BookManagementSpring.model.BookStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BookRequestDTO {
    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "ISBN is mandatory")
    private String isbn;

    @NotNull(message = "Publication date is mandatory")
    private LocalDate publicationDate;

    @NotBlank(message = "Language is mandatory")
    private String language;

    @NotNull(message = "Price is mandatory")
    private Double price;

    @NotNull(message = "Status is mandatory")
    private BookStatus status;

    @NotNull(message = "Author ID is mandatory")
    private Long author_id;  // This is assuming you're using the author's ID in the request

    public BookRequestDTO() {
    }

    public BookRequestDTO(String title, String isbn, LocalDate publicationDate, String language, Double price, BookStatus status, Long author_id) {
        this.title = title;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.language = language;
        this.price = price;
        this.status = status;
        this.author_id = author_id;
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

