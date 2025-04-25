package com.example.BookManagementSpring.service;


import com.example.BookManagementSpring.DTO.AuthorResponseDTO;
import com.example.BookManagementSpring.DTO.BookRequestDTO;
import com.example.BookManagementSpring.DTO.BookResponseDTO;
import com.example.BookManagementSpring.exception.AuthorNotFoundException;
import com.example.BookManagementSpring.exception.ResourceNotFoundException;
import com.example.BookManagementSpring.model.Author;
import com.example.BookManagementSpring.model.Book;
import com.example.BookManagementSpring.repository.AuthorRepository;
import com.example.BookManagementSpring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;



    public BookResponseDTO createBooks(BookRequestDTO bookRequestDTO) {
        Author author = authorRepository.findById(bookRequestDTO.getAuthor_id())
                .orElseThrow(()-> new RuntimeException("Author not found"));

        Book book = new Book();
        book.setTitle(bookRequestDTO.getTitle());
        book.setIsbn(bookRequestDTO.getIsbn());
        book.setPublicationDate(bookRequestDTO.getPublicationDate());
        book.setLanguage(bookRequestDTO.getLanguage());
        book.setPrice(bookRequestDTO.getPrice());
        book.setStatus(bookRequestDTO.getStatus());
        book.setAuthor(author);

        Book savedBook = bookRepository.save(book);

        BookResponseDTO responseDTO = new BookResponseDTO();
        responseDTO.setId(savedBook.getId());
        responseDTO.setTitle(savedBook.getTitle());
        responseDTO.setIsbn(savedBook.getIsbn());
        responseDTO.setPublicationDate(savedBook.getPublicationDate());
        responseDTO.setLanguage(savedBook.getLanguage());
        responseDTO.setPrice(savedBook.getPrice());
        responseDTO.setStatus(savedBook.getStatus());

        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
        authorResponseDTO.setId(savedBook.getAuthor().getId());
        authorResponseDTO.setFullName(savedBook.getAuthor().getFullName());
        authorResponseDTO.setNationality(savedBook.getAuthor().getNationality());
        authorResponseDTO.setBiography(savedBook.getAuthor().getBiography());
        authorResponseDTO.setEmail(savedBook.getAuthor().getEmail());

        responseDTO.setAuthor(authorResponseDTO);
        return responseDTO;

    }

    public List<BookResponseDTO> getAllBooks() {
        // Fetch all books from the repository
        List<Book> books = bookRepository.findAll();

        // Map the list of Book entities to a list of BookResponseDTOs
        return books.stream()
                .map(book -> {
                    AuthorResponseDTO authorDTO = null;
                    if (book.getAuthor() != null) {
                        authorDTO = new AuthorResponseDTO(
                                book.getAuthor().getId(),
                                book.getAuthor().getFullName(),
                                book.getAuthor().getNationality(),
                                book.getAuthor().getBiography(),
                                book.getAuthor().getEmail(),
                                book.getAuthor().getVerificationStatus()
                        );
                    }

                    return new BookResponseDTO(
                            book.getId(),
                            book.getTitle(),
                            book.getIsbn(),
                            book.getPublicationDate(),
                            book.getLanguage(),
                            book.getPrice(),
                            book.getStatus(),
                            null //just books alone needs to be listed out
                    );
                })
                .collect(Collectors.toList());
    }

    public BookResponseDTO getBookById(Long id) {
        // Fetch the book from the repository using the ID
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        // Map the Book entity to a BookResponseDTO
        BookResponseDTO bookResponseDTO = new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getPublicationDate(),
                book.getLanguage(),
                book.getPrice(),
                book.getStatus(),
                book.getAuthor() != null ? new AuthorResponseDTO(
                        book.getAuthor().getId(),
                        book.getAuthor().getFullName(),
                        book.getAuthor().getNationality(),
                        book.getAuthor().getBiography(),
                        book.getAuthor().getEmail(),
                        book.getAuthor().getVerificationStatus()
                ) : null // If the author is null, return null for the author details
        );

        return bookResponseDTO;
    }

    public Book updateBook(Long id, BookRequestDTO bookRequestDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        // Update book fields only
        existingBook.setTitle(bookRequestDTO.getTitle());
        existingBook.setIsbn(bookRequestDTO.getIsbn());
        existingBook.setPublicationDate(bookRequestDTO.getPublicationDate());
        existingBook.setLanguage(bookRequestDTO.getLanguage());
        existingBook.setPrice(bookRequestDTO.getPrice());
        existingBook.setStatus(bookRequestDTO.getStatus());

        // DO NOT update author
        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
        bookRepository.delete(book);
    }







}

