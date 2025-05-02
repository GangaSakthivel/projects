package com.example.documentApplication.repository;

import com.example.documentApplication.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
