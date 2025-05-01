package com.example.VehicleDocumentManagement.service;


import com.example.VehicleDocumentManagement.dto.DocumentResponseDTO;
import com.example.VehicleDocumentManagement.model.Document;
import com.example.VehicleDocumentManagement.model.Vehicle;
import com.example.VehicleDocumentManagement.repository.DocumentRepository;
import com.example.VehicleDocumentManagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    // Upload document (photo or file)
    public DocumentResponseDTO uploadDocument(Long vehicleId, MultipartFile file) throws IOException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        Document document = new Document();
        document.setFileName(file.getOriginalFilename());
        document.setDocumentType(file.getContentType());
        document.setFileData(file.getBytes());
        document.setVehicle(vehicle);

        Document savedDocument = documentRepository.save(document);

        return convertToResponseDTO(savedDocument);
    }

    public List<DocumentResponseDTO> getDocumentsByVehicle(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        List<Document> documents = documentRepository.findByVehicle(vehicle);
        return documents.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private DocumentResponseDTO convertToResponseDTO(Document document) {
        DocumentResponseDTO responseDTO = new DocumentResponseDTO();
        responseDTO.setId(document.getId());
        responseDTO.setFileName(document.getFileName());
        responseDTO.setDocumentType(document.getDocumentType());
        responseDTO.setFileUrl("/documents/" + document.getId());
        return responseDTO;
    }


}