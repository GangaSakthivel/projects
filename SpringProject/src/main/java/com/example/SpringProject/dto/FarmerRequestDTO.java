package com.example.SpringProject.dto;

import com.example.SpringProject.model.Status;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class FarmerRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Phone number is required")
    private String phoneNumber;

    @NotNull(message = "Address is required")
    private String address;

    @NotNull(message = "Status is required")
    private Status status;

}

