package com.example.DocumentManagementSystem.repository;

import com.example.DocumentManagementSystem.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
