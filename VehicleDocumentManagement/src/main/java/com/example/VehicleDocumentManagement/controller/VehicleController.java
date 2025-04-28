package com.example.VehicleDocumentManagement.controller;

import com.example.VehicleDocumentManagement.dto.VehicleRequestDTO;
import com.example.VehicleDocumentManagement.dto.VehicleResponseDTO;
import com.example.VehicleDocumentManagement.model.Vehicle;
import com.example.VehicleDocumentManagement.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;


    @PostMapping("/input")
    public ResponseEntity<VehicleResponseDTO> createVehicle(@Valid @RequestBody VehicleRequestDTO vehicleRequestDTO){
        VehicleResponseDTO responseDTO = vehicleService.createVehicle(vehicleRequestDTO);
        return ResponseEntity.ok(responseDTO);

    }

    @GetMapping
    public ResponseEntity<List<VehicleResponseDTO>> getAllVehicles(){
        List<VehicleResponseDTO> responseDTO = vehicleService.getAllVehicles();
        return ResponseEntity.ok(responseDTO);
    }

}
