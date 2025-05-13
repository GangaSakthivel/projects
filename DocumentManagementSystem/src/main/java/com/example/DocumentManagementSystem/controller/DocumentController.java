package com.example.DocumentManagementSystem.controller;

import com.example.DocumentManagementSystem.dto.DocumentRequestDTO;
import com.example.DocumentManagementSystem.dto.DocumentResponseDTO;
import com.example.DocumentManagementSystem.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/add-files")
    public ResponseEntity<DocumentResponseDTO> createDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("fileName") String fileName,
            @RequestParam("fileType") String fileType) {

        // Create DTO using received parameters
        DocumentRequestDTO requestDTO = new DocumentRequestDTO(fileName, fileType, file);

        // Call service to save the document, passing requestDTO to the service method
        DocumentResponseDTO responseDTO = documentService.createDocument(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

}
