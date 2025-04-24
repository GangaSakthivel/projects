package com.example.BookManagementSpring.repository;

import com.example.BookManagementSpring.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
