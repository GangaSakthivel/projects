
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

    // POST: Create a new LoadWeight
    @PostMapping
    public ResponseEntity<LoadWeightResponseDTO> createLoadWeight(@Valid @RequestBody LoadWeightRequestDTO loadWeightRequestDTO) {
        LoadWeightResponseDTO createdLoadWeight = loadWeightService.createLoadWeight(loadWeightRequestDTO);
        return new ResponseEntity<>(createdLoadWeight, HttpStatus.CREATED);
    }

    // GET: Get all LoadWeights
    @GetMapping
    public ResponseEntity<List<LoadWeightResponseDTO>> getAllLoadWeights() {
        List<LoadWeightResponseDTO> loadWeights = loadWeightService.getAllLoadWeights();
        return new ResponseEntity<>(loadWeights, HttpStatus.OK);
    }

    // GET: Get a LoadWeight by ID
    @GetMapping("/{id}")
    public ResponseEntity<LoadWeightResponseDTO> getLoadWeightById(@PathVariable Long id) {
        try {
            LoadWeightResponseDTO loadWeight = loadWeightService.getLoadWeightById(id);
            return new ResponseEntity<>(loadWeight, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            // Exception is already handled in the service; just return the ResponseEntity
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PUT: Update an existing LoadWeight
    @PutMapping("/{id}")
    public ResponseEntity<LoadWeightResponseDTO> updateLoadWeight(@PathVariable Long id, @Valid @RequestBody LoadWeightRequestDTO loadWeightRequestDTO) {
        try {
            LoadWeightResponseDTO updatedLoadWeight = loadWeightService.updateLoadWeight(id, loadWeightRequestDTO);
            return new ResponseEntity<>(updatedLoadWeight, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE: Delete a LoadWeight by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoadWeight(@PathVariable Long id) {
        try {
            loadWeightService.deleteLoadWeight(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

