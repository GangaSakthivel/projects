package com.example.SpringProject.dto;

import com.example.SpringProject.model.Status;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class FarmerRequestDTO {

    @NotBlank
    private String name;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String address;

    @NotNull
    private Status status;

}

