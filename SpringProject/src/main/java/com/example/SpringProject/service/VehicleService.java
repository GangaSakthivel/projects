package com.example.SpringProject.service;

import com.example.SpringProject.dto.VehicleRequestDTO;
import com.example.SpringProject.dto.VehicleResponseDTO;
import com.example.SpringProject.model.Vehicle;
import com.example.SpringProject.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    //entity to responseDTO
    private VehicleResponseDTO convertToResponseDTO(Vehicle vehicle) {
        VehicleResponseDTO responseDTO = new VehicleResponseDTO();
        responseDTO.setVehicleId(vehicle.getVehicleId());
        responseDTO.setVehicleNumber(vehicle.getVehicleNumber());
        responseDTO.setVehicleName(vehicle.getVehicleName());
        responseDTO.setStatus(vehicle.getStatus());
        responseDTO.setCreatedAt(vehicle.getCreatedAt());
        responseDTO.setUpdatedAt(vehicle.getUpdatedAt());
        return responseDTO;
    }

    public VehicleResponseDTO createVehicle(VehicleRequestDTO vehicleRequestDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(vehicleRequestDTO.getVehicleNumber());
        vehicle.setVehicleName(vehicleRequestDTO.getVehicleName());
        vehicle.setStatus(vehicleRequestDTO.getStatus());
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return convertToResponseDTO(savedVehicle);
    }

    public List<VehicleResponseDTO> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public VehicleResponseDTO getVehicleById(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            return convertToResponseDTO(vehicle.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with id: " + id);
        }
    }

    public VehicleResponseDTO updateVehicle(Long id, VehicleRequestDTO vehicleRequestDTO) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(id);
        if (existingVehicle.isPresent()) {
            Vehicle vehicleToUpdate = existingVehicle.get();
            vehicleToUpdate.setVehicleNumber(vehicleRequestDTO.getVehicleNumber());
            vehicleToUpdate.setVehicleName(vehicleRequestDTO.getVehicleName());
            vehicleToUpdate.setStatus(vehicleRequestDTO.getStatus());
            Vehicle updatedVehicle = vehicleRepository.save(vehicleToUpdate);
            return convertToResponseDTO(updatedVehicle);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with id: " + id);
        }
    }

    public void deleteVehicle(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            vehicleRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with id: " + id);
        }
    }
}