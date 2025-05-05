package com.example.SpringProject.dto;

import com.example.SpringProject.model.Status;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class VehicleRequestDTO {

    @NotBlank
    @Size(max = 255)
    private String vehicleNumber;

    @NotBlank
    @Size(max = 255)
    private String vehicleName;

    @NotNull
    private Status status;
}

