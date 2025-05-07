
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

    @PostMapping("/add-trader")
    public ResponseEntity<TraderResponseDTO> createTrader(@Valid @RequestBody TraderRequestDTO traderRequestDTO) {
        TraderResponseDTO createdTrader = traderService.createTrader(traderRequestDTO);
        return new ResponseEntity<>(createdTrader, HttpStatus.CREATED);
    }

    @GetMapping("/get-traders")
    public ResponseEntity<List<TraderResponseDTO>> getAllTraders() {
        List<TraderResponseDTO> traders = traderService.getAllTraders();
        return new ResponseEntity<>(traders, HttpStatus.OK);
    }

    @GetMapping("/get-trader/{id}")
    public ResponseEntity<TraderResponseDTO> getTraderById(@PathVariable Long id) {
        try {
            TraderResponseDTO trader = traderService.getTraderById(id);
            return new ResponseEntity<>(trader, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TraderResponseDTO> updateTrader(@PathVariable Long id, @Valid @RequestBody TraderRequestDTO traderRequestDTO) {
        try {
            TraderResponseDTO updatedTrader = traderService.updateTrader(id, traderRequestDTO);
            return new ResponseEntity<>(updatedTrader, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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
