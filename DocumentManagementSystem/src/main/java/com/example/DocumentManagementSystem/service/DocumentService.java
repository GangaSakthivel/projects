package com.example.DocumentManagementSystem.service;

import com.example.DocumentManagementSystem.dto.DocumentRequestDTO;
import com.example.DocumentManagementSystem.dto.DocumentResponseDTO;
import com.example.DocumentManagementSystem.model.Document;
import com.example.DocumentManagementSystem.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public DocumentResponseDTO createDocument(DocumentRequestDTO requestDTO) {
        try {
            // Convert MultipartFile to byte[] to store in the database
            byte[] fileData = requestDTO.getFileData().getBytes();

            // Create and save the Document entity
            Document document = new Document();
            document.setFileName(requestDTO.getFileName());
            document.setFileType(requestDTO.getFileType());
            document.setFileSize((long) fileData.length);
            document.setUploadTime(LocalDateTime.now());
            document.setFileData(fileData);

            // Save document to the database
            document = documentRepository.save(document);

            // Return the response DTO
            return new DocumentResponseDTO(document.getId(), document.getFileName(), document.getFileType());
        } catch (Exception e) {
            // Handle exception if file upload fails
            throw new RuntimeException("File upload failed", e);
        }
    }
}
