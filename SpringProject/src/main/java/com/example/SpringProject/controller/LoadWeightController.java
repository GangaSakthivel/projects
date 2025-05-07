
package com.example.SpringProject.controller;

import com.example.SpringProject.dto.LoadWeightRequestDTO;
import com.example.SpringProject.dto.LoadWeightResponseDTO;
import com.example.SpringProject.service.LoadWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/load-weights")
public class LoadWeightController {

    @Autowired
    private LoadWeightService loadWeightService;

    @PostMapping("/add/load-weight")
    public ResponseEntity<LoadWeightResponseDTO> createLoadWeight(@Valid @RequestBody LoadWeightRequestDTO loadWeightRequestDTO) {
        LoadWeightResponseDTO createdLoadWeight = loadWeightService.createLoadWeight(loadWeightRequestDTO);
        return new ResponseEntity<>(createdLoadWeight, HttpStatus.CREATED);
    }

    @GetMapping("get/load-weights")
    public ResponseEntity<List<LoadWeightResponseDTO>> getAllLoadWeights() {
        List<LoadWeightResponseDTO> loadWeights = loadWeightService.getAllLoadWeights();
        return new ResponseEntity<>(loadWeights, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoadWeightResponseDTO> getLoadWeightById(@PathVariable Long id) {
        try {
            LoadWeightResponseDTO loadWeight = loadWeightService.getLoadWeightById(id);
            return new ResponseEntity<>(loadWeight, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<LoadWeightResponseDTO> updateLoadWeight(@PathVariable Long id, @Valid @RequestBody LoadWeightRequestDTO loadWeightRequestDTO) {
        try {
            LoadWeightResponseDTO updatedLoadWeight = loadWeightService.updateLoadWeight(id, loadWeightRequestDTO);
            return new ResponseEntity<>(updatedLoadWeight, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoadWeight(@PathVariable Long id) {
        try {
            loadWeightService.deleteLoadWeight(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-vehicle-number/{vehicleNumber}")
    public List<LoadWeightResponseDTO> getLoadWeightsByVehicleNumber(@PathVariable String vehicleNumber) {
        return loadWeightService.getLoadWeightsByVehicleNumber(vehicleNumber);
    }
}

