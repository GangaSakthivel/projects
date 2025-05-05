package com.example.SpringProject.dto;

import com.example.SpringProject.model.Status; // Assuming Status is in this package
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class VehicleRequestDTO {

    @NotBlank(message = "Vehicle number is required")
    @Size(max = 255)
    private String vehicleNumber;

    @NotBlank(message = "Vehicle name is required")
    @Size(max = 255)
    private String vehicleName;

    @NotNull(message = "Status is required")
    private Status status;
}

