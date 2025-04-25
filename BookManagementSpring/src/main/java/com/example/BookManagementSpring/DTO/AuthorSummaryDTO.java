package com.example.BookManagementSpring.DTO;

import java.util.List;


public class AuthorSummaryDTO {
    private String fullName;
    private int bookCount;
    private List<String> bookTitles;

    public AuthorSummaryDTO() {
    }

    public AuthorSummaryDTO(String fullName, int bookCount, List<String> bookTitles) {
        this.fullName = fullName;
        this.bookCount = bookCount;
        this.bookTitles = bookTitles;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public List<String> getBookTitles() {
        return bookTitles;
    }

    public void setBookTitles(List<String> bookTitles) {
        this.bookTitles = bookTitles;
    }
}
