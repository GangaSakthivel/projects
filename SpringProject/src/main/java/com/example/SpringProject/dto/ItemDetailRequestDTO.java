package com.example.SpringProject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ItemDetailRequestDTO {

    @NotNull
    private Double value;

    @NotNull
    private int count;

}

