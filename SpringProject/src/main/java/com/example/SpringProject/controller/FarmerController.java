package com.example.SpringProject.controller;

import com.example.SpringProject.dto.FarmerRequestDTO;
import com.example.SpringProject.dto.FarmerResponseDTO;
import com.example.SpringProject.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @PostMapping("/input-farmer")
    public ResponseEntity<FarmerResponseDTO> createFarmer(@Valid @RequestBody FarmerRequestDTO farmerRequestDTO) {
        FarmerResponseDTO createdFarmer = farmerService.createFarmer(farmerRequestDTO);
        return new ResponseEntity<>(createdFarmer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FarmerResponseDTO>> getAllFarmers() {
        List<FarmerResponseDTO> farmers = farmerService.getAllFarmers();
        return new ResponseEntity<>(farmers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmerResponseDTO> getFarmerById(@PathVariable Long id) {
        try {
            FarmerResponseDTO farmer = farmerService.getFarmerById(id);
            return new ResponseEntity<>(farmer, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmerResponseDTO> updateFarmer(@PathVariable Long id, @Valid @RequestBody FarmerRequestDTO farmerRequestDTO) {
        try {
            FarmerResponseDTO updatedFarmer = farmerService.updateFarmer(id, farmerRequestDTO);
            return new ResponseEntity<>(updatedFarmer, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmer(@PathVariable Long id) {
        try {
            farmerService.deleteFarmer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
