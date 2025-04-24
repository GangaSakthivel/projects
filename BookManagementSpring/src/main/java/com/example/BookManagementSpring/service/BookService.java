package com.example.BookManagementSpring.service;


import com.example.BookManagementSpring.DTO.BookRequestDTO;
import com.example.BookManagementSpring.DTO.BookResponseDTO;
import com.example.BookManagementSpring.exception.AuthorNotFoundException;
import com.example.BookManagementSpring.model.Author;
import com.example.BookManagementSpring.model.Book;
import com.example.BookManagementSpring.repository.AuthorRepository;
import com.example.BookManagementSpring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;


    public BookResponseDTO createBooks(BookRequestDTO bookRequestDTO) {
        //convert request to entity
        Book book = new Book();
        book.setTitle(bookRequestDTO.getTitle());
        book.setIsbn(bookRequestDTO.getIsbn());
        book.setPublicationDate(bookRequestDTO.getPublicationDate());
        book.setLanguage(bookRequestDTO.getLanguage());
        book.setPrice(bookRequestDTO.getPrice());
        book.setStatus(bookRequestDTO.getStatus());

        // Fetch author using author_id
        Author author = authorRepository.findById(bookRequestDTO.getAuthor_id())
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with ID: " + bookRequestDTO.getAuthor_id()));

        book.setAuthor(author);

        Book savedBook = bookRepository.save(book);

        // Convert Entity to Response DTO
        BookResponseDTO responseDTO = new BookResponseDTO();
        responseDTO.setId(savedBook.getId());
        responseDTO.setTitle(savedBook.getTitle());
        responseDTO.setIsbn(savedBook.getIsbn());
        responseDTO.setPublicationDate(savedBook.getPublicationDate());
        responseDTO.setLanguage(savedBook.getLanguage());
        responseDTO.setPrice(savedBook.getPrice());
        responseDTO.setStatus(savedBook.getStatus());
        responseDTO.setAuthor_id(savedBook.getAuthor().getId());

        return responseDTO;
    }

        public List<BookResponseDTO> getAllBooks() {
            // Fetch all books from the repository
            List<Book> books = bookRepository.findAll();

            // Map the list of Book entities to a list of BookResponseDTOs
            List<BookResponseDTO> bookResponseDTOs = books.stream()
                    .map(book -> new BookResponseDTO(
                            book.getId(),
                            book.getTitle(),
                            book.getIsbn(),
                            book.getPublicationDate(),
                            book.getLanguage(),
                            book.getPrice(),
                            book.getStatus(),
                            book.getAuthor() != null ? book.getAuthor().getId() : null))  // Ensure the author is not null
                    .collect(Collectors.toList());
            return bookResponseDTOs;  // Return the list of DTOs
        }

}
