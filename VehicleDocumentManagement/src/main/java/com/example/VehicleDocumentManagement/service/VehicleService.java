package com.example.VehicleDocumentManagement.service;

import com.example.VehicleDocumentManagement.dto.VehicleRequestDTO;
import com.example.VehicleDocumentManagement.dto.VehicleResponseDTO;
import com.example.VehicleDocumentManagement.model.Vehicle;
import com.example.VehicleDocumentManagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    
    @Autowired
    private VehicleRepository vehicleRepository;


    public VehicleResponseDTO createVehicle(VehicleRequestDTO vehicleRequestDTO) {

        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleNumber(vehicleRequestDTO.getVehicleNumber());
        vehicle.setVehicleType(vehicleRequestDTO.getVehicleType());
        vehicle.setRunningKm(vehicleRequestDTO.getRunningKm());
        vehicle.setNextServiceKm(vehicleRequestDTO.getNextServiceKm());
        vehicle.setFuelTankCapacity(vehicleRequestDTO.getFuelTankCapacity());
        vehicle.setVehicleAvailability(vehicleRequestDTO.isVehicleAvailability());
        vehicle.setNotes(vehicleRequestDTO.getNotes());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        VehicleResponseDTO responseDTO = new VehicleResponseDTO();

        responseDTO.setId(savedVehicle.getId());
        responseDTO.setVehicleNumber(savedVehicle.getVehicleNumber());
        responseDTO.setVehicleType(savedVehicle.getVehicleType());
        responseDTO.setRunningKm(savedVehicle.getRunningKm());
        responseDTO.setNextServiceKm(savedVehicle.getNextServiceKm());
        responseDTO.setFuelTankCapacity(savedVehicle.getFuelTankCapacity());
        responseDTO.setVehicleAvailability(savedVehicle.isVehicleAvailability());
        responseDTO.setNotes(savedVehicle.getNotes());

        return responseDTO;

    }

    public List<VehicleResponseDTO> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();

        // Convert the list of Vehicle entities to VehicleResponseDTOs
        List<VehicleResponseDTO> responseDTO = vehicles.stream()
                .map(vehicle -> {
                    // Create a new VehicleResponseDTO and set values from the Vehicle entity
                    VehicleResponseDTO dto = new VehicleResponseDTO();
                    dto.setId(vehicle.getId());
                    dto.setVehicleNumber(vehicle.getVehicleNumber());
                    dto.setVehicleType(vehicle.getVehicleType());
                    dto.setRunningKm(vehicle.getRunningKm());
                    dto.setNextServiceKm(vehicle.getNextServiceKm());
                    dto.setFuelTankCapacity(vehicle.getFuelTankCapacity());
                    dto.setVehicleAvailability(vehicle.isVehicleAvailability());
                    dto.setNotes(vehicle.getNotes());

                    // Return the mapped DTO
                    return dto;
                })
                .collect(Collectors.toList()); // Collect all mapped DTOs into a list

        // Return the list of VehicleResponseDTOs
        return responseDTO;
    }

}
