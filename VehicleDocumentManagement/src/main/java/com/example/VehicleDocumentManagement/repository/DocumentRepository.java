package com.example.VehicleDocumentManagement.repository;

import com.example.VehicleDocumentManagement.model.Document;
import com.example.VehicleDocumentManagement.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByVehicle(Vehicle vehicle);
}
