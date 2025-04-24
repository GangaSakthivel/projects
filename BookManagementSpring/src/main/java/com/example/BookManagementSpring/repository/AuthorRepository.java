package com.example.BookManagementSpring.repository;

import com.example.BookManagementSpring.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
