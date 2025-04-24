package com.example.BookManagementSpring.controller;


import com.example.BookManagementSpring.DTO.AuthorRequestDTO;
import com.example.BookManagementSpring.DTO.AuthorResponseDTO;
import com.example.BookManagementSpring.exception.DuplicateAuthorException;
import com.example.BookManagementSpring.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/input")
    public ResponseEntity<AuthorResponseDTO> createAuthor(@Valid @RequestBody AuthorRequestDTO authorRequestDTO){
        try {
            AuthorResponseDTO authorResponse = authorService.createAuthor(authorRequestDTO);
            return new ResponseEntity<>(authorResponse, HttpStatus.CREATED);
        }catch (DuplicateAuthorException de){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors(){
        List<AuthorResponseDTO> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id){
        AuthorResponseDTO authors = authorService.getAuthorById(id);
        return ResponseEntity.ok(authors);
    }

    @PutMapping("{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorRequestDTO authorRequestDTO){
        AuthorResponseDTO updateResponse = authorService.updateAuthor(id, authorRequestDTO);
        return ResponseEntity.ok(updateResponse);
        }



    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthor(id);
        return ResponseEntity.ok("Deleted successfully");

    }
}

