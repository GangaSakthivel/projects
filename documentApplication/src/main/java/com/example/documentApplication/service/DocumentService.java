package com.example.documentApplication.service;

import com.example.documentApplication.DTO.DocumentResponseDTO;
import com.example.documentApplication.model.Document;
import com.example.documentApplication.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public DocumentResponseDTO saveDocument(String title, String description, MultipartFile file) throws IOException {
        Document document = new Document();
        document.setTitle(title);
        document.setDescription(description);
        document.setFileName(file.getOriginalFilename());
        document.setFileType(file.getContentType());
        document.setFileData(file.getBytes());
        document.setUploadedAt(LocalDateTime.now());

        Document saved = documentRepository.save(document);
        return mapToDTO(saved);
    }

    public List<DocumentResponseDTO> getAllDocuments() {
        return documentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Document getDocumentFileById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found with id " + id));
    }

    public void deleteDocument(Long id) {
        if (!documentRepository.existsById(id)) {
            throw new RuntimeException("Document not found with id " + id);
        }
        documentRepository.deleteById(id);
    }

    private DocumentResponseDTO mapToDTO(Document doc) {
        DocumentResponseDTO dto = new DocumentResponseDTO();
        dto.setId(doc.getId());
        dto.setTitle(doc.getTitle());
        dto.setDescription(doc.getDescription());
        dto.setFileName(doc.getFileName());
        dto.setFileType(doc.getFileType());
        dto.setUploadedAt(doc.getUploadedAt());
        return dto;
    }
}