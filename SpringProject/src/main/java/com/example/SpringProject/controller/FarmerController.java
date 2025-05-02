package com.example.SpringProject.controller;

import com.example.SpringProject.model.Farmer;
import com.example.SpringProject.repository.FarmerRepository;
import com.example.SpringProject.service.FarmerService;
import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private FarmerService farmerService;

    @PostMapping
    public ResponseEntity<String> createFarmer(@RequestBody Farmer farmer) {
        if (farmer.getLoadWeights() == null) {
            farmer.setLoadWeights(new ArrayList<>());
        }
        farmerRepository.save(farmer);
        return ResponseEntity.ok("Farmer saved successfully");
    }

    @GetMapping
    public ResponseEntity<List<Farmer>> getAllFarmers() {
        return ResponseEntity.ok(farmerService.getAllFarmers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Farmer> getFarmerById(@PathVariable Long id) {
        Optional<Farmer> farmer = farmerService.getFarmerById(id);
        return farmer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Farmer> updateFarmer(@PathVariable Long id, @RequestBody Farmer updatedFarmer) {
        Farmer updated = farmerService.updateFarmer(id, updatedFarmer);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmer(@PathVariable Long id) {
        farmerService.deleteFarmer(id);
        return ResponseEntity.noContent().build();
    }
}
