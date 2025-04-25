package com.example.BookManagementSpring.controller;


import com.example.BookManagementSpring.DTO.AuthorResponseDTO;
import com.example.BookManagementSpring.DTO.BookRequestDTO;
import com.example.BookManagementSpring.DTO.BookResponseDTO;
import com.example.BookManagementSpring.model.Book;
import com.example.BookManagementSpring.service.AuthorService;
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
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @PostMapping("/input")
    public ResponseEntity<BookResponseDTO> createBooks(@Valid @RequestBody BookRequestDTO bookRequestDTO){
            BookResponseDTO bookResponseDTO = bookService.createBooks(bookRequestDTO);
            return new ResponseEntity<>(bookResponseDTO, HttpStatus.CREATED);
    }

    //here we take book request as input
    //

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
//
//
@PutMapping("/{id}")
public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @RequestBody BookRequestDTO bookRequestDTO) {
    Book updatedBook = bookService.updateBook(id, bookRequestDTO);

    BookResponseDTO responseDTO = new BookResponseDTO(
            updatedBook.getId(),
            updatedBook.getTitle(),
            updatedBook.getIsbn(),
            updatedBook.getPublicationDate(),
            updatedBook.getLanguage(),
            updatedBook.getPrice(),
            updatedBook.getStatus(),
            new AuthorResponseDTO(
                    updatedBook.getAuthor().getId(),
                    updatedBook.getAuthor().getFullName(),
                    updatedBook.getAuthor().getNationality(),
                    updatedBook.getAuthor().getBiography(),
                    updatedBook.getAuthor().getEmail(),
                    updatedBook.getAuthor().getVerificationStatus()
            )
    );

    return ResponseEntity.ok(responseDTO);
}

//
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book with the " + id + " was deleted successfully.");
    }






}
