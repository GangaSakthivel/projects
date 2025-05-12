package com.example.DocumentManagementSystem.controller;

import com.example.DocumentManagementSystem.dto.DocumentRequestDTO;
import com.example.DocumentManagementSystem.dto.DocumentResponseDTO;
import com.example.DocumentManagementSystem.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }


    public ResponseEntity<DocumentResponseDTO> createDocument(@RequestBody DocumentRequestDTO requestDTO){
        DocumentResponseDTO responseDTO = documentService.createDocument(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

}
