package com.example.BookManagementSpring.controller;


import com.example.BookManagementSpring.DTO.AuthorResponseDTO;
import com.example.BookManagementSpring.DTO.BookRequestDTO;
import com.example.BookManagementSpring.DTO.BookResponseDTO;
import com.example.BookManagementSpring.model.Book;
import com.example.BookManagementSpring.repository.BookRepository;
import com.example.BookManagementSpring.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/input")
    public ResponseEntity<BookResponseDTO> createBooks(@Valid @RequestBody BookRequestDTO bookRequestDTO){
            BookResponseDTO bookResponseDTO = bookService.createBooks(bookRequestDTO);
            return new ResponseEntity<>(bookResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        List<BookResponseDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }






}
