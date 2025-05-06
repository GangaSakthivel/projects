package com.example.SpringProject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ItemDetailRequestDTO {

    @NotNull(message = "Value is required")
    private Double value;

    @NotNull(message = "Count is required")
    private int count;

}

