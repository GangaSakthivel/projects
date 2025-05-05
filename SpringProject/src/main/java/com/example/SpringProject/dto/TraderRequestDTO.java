package com.example.SpringProject.dto;

import com.example.SpringProject.model.Status; // Assuming Status is in this package
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

// Request DTO: What the client sends to create/update a Trader
@Data
public class TraderRequestDTO {

    @NotBlank(message = "Trader name is required")
    private String traderName;

    @NotNull(message = "Phone number is required")
    private String phoneNumber;

    @NotNull(message = "Address is required")
    private String address;

    @NotNull(message = "Status is required")
    private Status status;
}


