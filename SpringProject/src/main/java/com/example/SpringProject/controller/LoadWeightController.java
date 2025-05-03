package com.example.SpringProject.controller;

import com.example.SpringProject.dto.LoadWeightReportResponse;
import com.example.SpringProject.dto.LoadWeightRequestDTO;
import com.example.SpringProject.dto.LoadWeightResponseDTO;
import com.example.SpringProject.model.LoadWeight;
import com.example.SpringProject.model.Vehicle;
import com.example.SpringProject.repository.LoadWeightRepository;
import com.example.SpringProject.repository.VehicleRepository;
import com.example.SpringProject.service.LoadWeightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/load-weight")
public class LoadWeightController {
    @Autowired
    private LoadWeightRepository loadWeightRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private LoadWeightService loadWeightService;

    @PostMapping
    public ResponseEntity<LoadWeightResponseDTO> createLoadWeight(@Valid @RequestBody LoadWeightRequestDTO loadWeightRequestDTO) {
        // Find the vehicle by ID from the DTO
        Vehicle vehicle = vehicleRepository.findById(loadWeightRequestDTO.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        Optional<LoadWeight> existingLoadWeight = loadWeightRepository.findByVehicle(vehicle);
        if (existingLoadWeight.isPresent()) {
            throw new RuntimeException("LoadWeight already exists for this vehicle");
        }

        // If no existing LoadWeight, proceed to service layer to create a new LoadWeight
        return loadWeightService.createLoadWeight(loadWeightRequestDTO);
    }



    @GetMapping("/{id}")
    public ResponseEntity<LoadWeightResponseDTO> getLoadWeightById(@PathVariable Long id) {
        return ResponseEntity.ok(loadWeightService.getLoadWeightById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLoadWeight(@PathVariable Long id) {
        loadWeightService.deleteLoadWeight(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @GetMapping("/vehicle/{vehicleNumber}")
    public ResponseEntity<LoadWeightReportResponse> getReportByVehicleNumber(@PathVariable String vehicleNumber) {
        Vehicle vehicle = vehicleRepository.findByVehicleNumber(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        LoadWeight loadWeight = loadWeightRepository.findByVehicle(vehicle)
                .orElseThrow(() -> new RuntimeException("LoadWeight not found for this vehicle"));

        LoadWeightReportResponse response = new LoadWeightReportResponse();
        response.setVehicleNumber(vehicle.getVehicleNumber());
        response.setVehicleName(vehicle.getVehicleName());
        response.setFarmerName(loadWeight.getFarmer().getName());
        response.setTraderName(loadWeight.getTrader().getTraderName());
        response.setEmptyWeight(loadWeight.getEmpty());
        response.setLoadWeight(loadWeight.getLoad());
        response.setNetWeight(loadWeight.getLoad() - loadWeight.getEmpty());
        response.setStatus(loadWeight.getStatus().toString());
        response.setCreatedAt(loadWeight.getCreatedAt());
        response.setUpdatedAt(loadWeight.getUpdatedAt());
        response.setItemDetails(loadWeight.getItemDetails());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/load-weight")
    public ResponseEntity<LoadWeight> createLoadWeight(@RequestBody LoadWeight loadWeight) {
        LoadWeight savedLoadWeight = loadWeightService.saveLoadWeight(loadWeight);
        return ResponseEntity.ok(savedLoadWeight);
    }


}

