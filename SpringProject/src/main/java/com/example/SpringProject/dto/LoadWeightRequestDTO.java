package com.example.SpringProject.dto;

import com.example.SpringProject.model.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

import lombok.Data;

@Data
public class LoadWeightRequestDTO {

    @NotNull(message = "Number is required")
    @Positive(message = "Number must be positive")
    private Long number;

    @NotNull(message = "Empty weight is required")
    @Positive(message = "Empty weight must be positive")
    private Double empty;

    @NotNull(message = "Load weight is required")
    @Positive(message = "Load weight must be positive")
    private Double load;

    @NotNull(message = "Cages is required")
    @Positive(message = "Cages must be positive")
    private Integer cages;

    @NotNull(message = "Status is required")
    private Status status;

    @NotNull(message = "Farmer ID is required")
    private Long farmerId;

    @NotNull(message = "Trader ID is required")
    private Long traderId;

    @NotNull(message = "Vehicle ID is required")
    private Long vehicleId;

    @NotNull(message = "Item details are required")
    private List<ItemDetailRequestDTO> itemDetails;
}
