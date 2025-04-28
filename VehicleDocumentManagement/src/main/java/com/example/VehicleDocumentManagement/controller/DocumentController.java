package com.example.VehicleDocumentManagement.controller;


import com.example.VehicleDocumentManagement.model.Document;
import com.example.VehicleDocumentManagement.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/uploadDocument")
    public ResponseEntity<Document> createDocument(@RequestParam("document") MultipartFile document,
                                                   @RequestParam("vehicleId") Long vehicleId) {
        // Call service to save the document and associate it with a vehicle
        Document savedDocument = documentService.createDocument(document, vehicleId);

        return ResponseEntity.ok(savedDocument);
    }



}
