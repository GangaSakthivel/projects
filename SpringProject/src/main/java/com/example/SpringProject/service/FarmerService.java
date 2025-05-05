package com.example.SpringProject.service;

import com.example.SpringProject.dto.FarmerRequestDTO;
import com.example.SpringProject.dto.FarmerResponseDTO;
import com.example.SpringProject.model.Farmer;
import com.example.SpringProject.repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    // Helper method to convert Farmer entity to FarmerResponseDTO
    private FarmerResponseDTO convertToResponseDTO(Farmer farmer) {
        FarmerResponseDTO responseDTO = new FarmerResponseDTO();
        responseDTO.setId(farmer.getId());
        responseDTO.setName(farmer.getName());
        responseDTO.setPhoneNumber(farmer.getPhoneNumber());
        responseDTO.setAddress(farmer.getAddress());
        responseDTO.setStatus(farmer.getStatus());
        responseDTO.setCreatedAt(farmer.getCreatedAt());
        responseDTO.setUpdatedAt(farmer.getUpdatedAt());
        return responseDTO;
    }

    // POST: Create a new Farmer
    public FarmerResponseDTO createFarmer(FarmerRequestDTO farmerRequestDTO) {
        Farmer farmer = new Farmer();
        farmer.setName(farmerRequestDTO.getName());
        farmer.setPhoneNumber(farmerRequestDTO.getPhoneNumber());
        farmer.setAddress(farmerRequestDTO.getAddress());
        farmer.setStatus(farmerRequestDTO.getStatus());
        // createdAt and updatedAt will be handled by @PrePersist in the Entity
        Farmer savedFarmer = farmerRepository.save(farmer);
        return convertToResponseDTO(savedFarmer);
    }

    // GET: Get all Farmers
    public List<FarmerResponseDTO> getAllFarmers() {
        List<Farmer> farmers = farmerRepository.findAll();
        return farmers.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // GET: Get a Farmer by ID
    public FarmerResponseDTO getFarmerById(Long id) {
        Optional<Farmer> farmer = farmerRepository.findById(id);
        if (farmer.isPresent()) {
            return convertToResponseDTO(farmer.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Farmer not found with id: " + id);
        }
    }

    // PUT: Update a Farmer by ID
    public FarmerResponseDTO updateFarmer(Long id, FarmerRequestDTO farmerRequestDTO) {
        Optional<Farmer> existingFarmer = farmerRepository.findById(id);
        if (existingFarmer.isPresent()) {
            Farmer farmerToUpdate = existingFarmer.get();
            farmerToUpdate.setName(farmerRequestDTO.getName());
            farmerToUpdate.setPhoneNumber(farmerRequestDTO.getPhoneNumber());
            farmerToUpdate.setAddress(farmerRequestDTO.getAddress());
            farmerToUpdate.setStatus(farmerRequestDTO.getStatus());
            // updatedAt will be handled by @PreUpdate in the Entity
            Farmer updatedFarmer = farmerRepository.save(farmerToUpdate);
            return convertToResponseDTO(updatedFarmer);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Farmer not found with id: " + id);
        }
    }

    // DELETE: Delete a Farmer by ID
    public void deleteFarmer(Long id) {
        Optional<Farmer> farmer = farmerRepository.findById(id);
        if (farmer.isPresent()) {
            farmerRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Farmer not found with id: " + id);
        }
    }
}
