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

    // POST: Create a new Farmer
    @PostMapping
    public ResponseEntity<FarmerResponseDTO> createFarmer(@Valid @RequestBody FarmerRequestDTO farmerRequestDTO) {
        FarmerResponseDTO createdFarmer = farmerService.createFarmer(farmerRequestDTO);
        return new ResponseEntity<>(createdFarmer, HttpStatus.CREATED);
    }

    // GET: Get all Farmers
    @GetMapping
    public ResponseEntity<List<FarmerResponseDTO>> getAllFarmers() {
        List<FarmerResponseDTO> farmers = farmerService.getAllFarmers();
        return new ResponseEntity<>(farmers, HttpStatus.OK);
    }

    // GET: Get a Farmer by ID
    @GetMapping("/{id}")
    public ResponseEntity<FarmerResponseDTO> getFarmerById(@PathVariable Long id) {
        try {
            FarmerResponseDTO farmer = farmerService.getFarmerById(id);
            return new ResponseEntity<>(farmer, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            //  Exception is already handled in the service,  just return the ResponseEntity
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or, you could re-throw, but it's not necessary here.
        }
    }

    // PUT: Update a Farmer by ID
    @PutMapping("/{id}")
    public ResponseEntity<FarmerResponseDTO> updateFarmer(@PathVariable Long id, @Valid @RequestBody FarmerRequestDTO farmerRequestDTO) {
        try {
            FarmerResponseDTO updatedFarmer = farmerService.updateFarmer(id, farmerRequestDTO);
            return new ResponseEntity<>(updatedFarmer, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE: Delete a Farmer by ID
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
