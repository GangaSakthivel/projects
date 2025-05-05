package com.example.SpringProject.dto;

import com.example.SpringProject.model.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VehicleResponseDTO {
    private Long vehicleId;
    private String vehicleNumber;
    private String vehicleName;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
