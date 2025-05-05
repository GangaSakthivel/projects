
package com.example.SpringProject.controller;

import com.example.SpringProject.dto.TraderRequestDTO;
import com.example.SpringProject.dto.TraderResponseDTO;
import com.example.SpringProject.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/traders")
public class TraderController {

    @Autowired
    private TraderService traderService;

    // POST: Create a new Trader
    @PostMapping
    public ResponseEntity<TraderResponseDTO> createTrader(@Valid @RequestBody TraderRequestDTO traderRequestDTO) {
        TraderResponseDTO createdTrader = traderService.createTrader(traderRequestDTO);
        return new ResponseEntity<>(createdTrader, HttpStatus.CREATED);
    }

    // GET: Get all Traders
    @GetMapping
    public ResponseEntity<List<TraderResponseDTO>> getAllTraders() {
        List<TraderResponseDTO> traders = traderService.getAllTraders();
        return new ResponseEntity<>(traders, HttpStatus.OK);
    }

    // GET: Get a Trader by ID
    @GetMapping("/{id}")
    public ResponseEntity<TraderResponseDTO> getTraderById(@PathVariable Long id) {
        try {
            TraderResponseDTO trader = traderService.getTraderById(id);
            return new ResponseEntity<>(trader, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            // Exception is already handled in the service; just return the ResponseEntity
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PUT: Update a Trader by ID
    @PutMapping("/{id}")
    public ResponseEntity<TraderResponseDTO> updateTrader(@PathVariable Long id, @Valid @RequestBody TraderRequestDTO traderRequestDTO) {
        try {
            TraderResponseDTO updatedTrader = traderService.updateTrader(id, traderRequestDTO);
            return new ResponseEntity<>(updatedTrader, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE: Delete a Trader by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrader(@PathVariable Long id) {
        try {
            traderService.deleteTrader(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
