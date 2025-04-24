package com.example.BookManagementSpring.controller;


import com.example.BookManagementSpring.DTO.BookRequestDTO;
import com.example.BookManagementSpring.DTO.BookResponseDTO;
import com.example.BookManagementSpring.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id){
        BookResponseDTO books = bookService.getBookById(id);
        return ResponseEntity.ok(books);
    }


    @PutMapping("{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @RequestBody BookRequestDTO bookRequestDTO){
        BookResponseDTO updateBook = bookService.updateBook(id, bookRequestDTO);
        return ResponseEntity.ok(updateBook);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book with the " + id + " was deleted successfully.");
    }






}
