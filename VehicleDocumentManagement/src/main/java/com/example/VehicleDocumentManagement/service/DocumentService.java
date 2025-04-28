package com.example.VehicleDocumentManagement.service;

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


//    public Document createDocument(MultipartFile document, Long vehicleId) {
//    }
}
