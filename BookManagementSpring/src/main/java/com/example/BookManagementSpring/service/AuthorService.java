package com.example.BookManagementSpring.service;

import com.example.BookManagementSpring.DTO.AuthorRequestDTO;
import com.example.BookManagementSpring.DTO.AuthorResponseDTO;
import com.example.BookManagementSpring.exception.AuthorNotFoundException;
import com.example.BookManagementSpring.exception.ResourceNotFoundException;
import com.example.BookManagementSpring.model.Author;
import com.example.BookManagementSpring.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO) {

        //converting author request dto to author entity
        Author author = new Author();
        author.setFullName(authorRequestDTO.getFullName());
        author.setNationality(authorRequestDTO.getNationality());
        author.setBiography(authorRequestDTO.getBiography());
        author.setEmail(authorRequestDTO.getEmail());
        author.setVerificationStatus(authorRequestDTO.getVerificationStatus()
        );

        Author savedAuthor = authorRepository.save(author); //saving details in the author repository

        //converting the saved author to response entity

        return new AuthorResponseDTO(
                savedAuthor.getId(),
                savedAuthor.getFullName(),
                savedAuthor.getNationality(),
                savedAuthor.getBiography(),
                savedAuthor.getEmail(),
                savedAuthor.getVerificationStatus()
        );
    }

    public List<AuthorResponseDTO> getAllAuthors(){
        List<Author> authors = authorRepository.findAll();

        List<AuthorResponseDTO> authorResponseDTOS = authors.stream()
                .map(author -> new AuthorResponseDTO(
                        author.getId(),
                        author.getFullName(),
                        author.getNationality(),
                        author.getBiography(),
                        author.getVerificationStatus()
                )).collect(Collectors.toList());


        return authorResponseDTOS;
    }

    public AuthorResponseDTO getAuthorById(Long id) {
        // Fetch the author by ID from the repository
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + id));

        // Convert the Author entity to AuthorResponseDTO and return it
        return new AuthorResponseDTO(
                author.getId(),
                author.getFullName(),
                author.getNationality(),
                author.getBiography(),
                author.getVerificationStatus()
        );
    }

    public AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO authorRequestDTO) {
        // Step 1: Fetch the existing author from the database
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));

        // Step 2: Update the fields
        existingAuthor.setFullName(authorRequestDTO.getFullName());
        existingAuthor.setNationality(authorRequestDTO.getNationality());
        existingAuthor.setBiography(authorRequestDTO.getBiography());
        existingAuthor.setVerificationStatus(authorRequestDTO.getVerificationStatus());

        // Step 3: Save the updated author
        Author updatedAuthor = authorRepository.save(existingAuthor);

        // Step 4: Convert to response DTO
        return new AuthorResponseDTO(
                updatedAuthor.getId(),
                updatedAuthor.getFullName(),
                updatedAuthor.getNationality(),
                updatedAuthor.getBiography(),
                updatedAuthor.getVerificationStatus(),
                updatedAuthor.g
        );
    }

    public void deleteAuthor(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty()) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found");
        }
        authorRepository.deleteById(id);
    }
}
