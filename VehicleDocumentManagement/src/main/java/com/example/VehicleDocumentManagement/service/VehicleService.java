package com.example.VehicleDocumentManagement.service;

import com.example.VehicleDocumentManagement.dto.DocumentResponseDTO;
import com.example.VehicleDocumentManagement.dto.VehicleRequestDTO;
import com.example.VehicleDocumentManagement.dto.VehicleResponseDTO;
import com.example.VehicleDocumentManagement.model.Vehicle;
import com.example.VehicleDocumentManagement.repository.DocumentRepository;
import com.example.VehicleDocumentManagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private DocumentRepository documentRepository;

    // Create or Update a Vehicle
    public VehicleResponseDTO createVehicle(VehicleRequestDTO vehicleRequestDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(vehicleRequestDTO.getVehicleNumber());
        vehicle.setVehicleName(vehicleRequestDTO.getVehicleName());
        vehicle.setVehicleType(vehicleRequestDTO.getVehicleType());
        vehicle.setRunningKm(vehicleRequestDTO.getRunningKm());
        vehicle.setNextServiceKm(vehicleRequestDTO.getNextServiceKm());
        vehicle.setNotes(vehicleRequestDTO.getNotes());
        vehicle.setVehicleAvailability(vehicleRequestDTO.isVehicleAvailability());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return convertToResponseDTO(savedVehicle);
    }

    // Get all Vehicles
    public List<VehicleResponseDTO> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get a Vehicle by ID
    public VehicleResponseDTO getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        return convertToResponseDTO(vehicle);
    }

    // Update a Vehicle
    public VehicleResponseDTO updateVehicle(Long id, VehicleRequestDTO vehicleRequestDTO) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setVehicleNumber(vehicleRequestDTO.getVehicleNumber());
        vehicle.setVehicleName(vehicleRequestDTO.getVehicleName());
        vehicle.setVehicleType(vehicleRequestDTO.getVehicleType());
        vehicle.setRunningKm(vehicleRequestDTO.getRunningKm());
        vehicle.setNextServiceKm(vehicleRequestDTO.getNextServiceKm());
        vehicle.setNotes(vehicleRequestDTO.getNotes());
        vehicle.setVehicleAvailability(vehicleRequestDTO.isVehicleAvailability());

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);

        return convertToResponseDTO(updatedVehicle);
    }

    // Delete a Vehicle
    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        vehicleRepository.delete(vehicle);
    }

    // Helper method to convert Vehicle to VehicleResponseDTO
    private VehicleResponseDTO convertToResponseDTO(Vehicle vehicle) {
        VehicleResponseDTO responseDTO = new VehicleResponseDTO();
        responseDTO.setId(vehicle.getId());
        responseDTO.setVehicleNumber(vehicle.getVehicleNumber());
        responseDTO.setVehicleName(vehicle.getVehicleName());
        responseDTO.setVehicleType(vehicle.getVehicleType());
        responseDTO.setRunningKm(vehicle.getRunningKm());
        responseDTO.setNextServiceKm(vehicle.getNextServiceKm());
        responseDTO.setNotes(vehicle.getNotes());
        responseDTO.setVehicleAvailability(vehicle.isVehicleAvailability());

        // You can add documents as well, for example, for the VehicleResponseDTO
        List<DocumentResponseDTO> documentResponseDTOs = documentRepository.findByVehicle(vehicle)
                .stream()
                .map(document -> new DocumentResponseDTO(
                        document.getId(),
                        document.getDocumentType(),
                        document.getFileName(),
                        document.getFileUrl()))
                .collect(Collectors.toList());
        responseDTO.setDocuments(documentResponseDTOs);

        return responseDTO;
    }




}