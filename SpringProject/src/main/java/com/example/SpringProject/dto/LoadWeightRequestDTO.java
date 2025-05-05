package com.example.SpringProject.dto;

import com.example.SpringProject.model.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

import lombok.Data;

@Data
public class LoadWeightRequestDTO {

    @NotNull
    @Positive
    private Long number;

    @NotNull
    @Positive
    private Double empty;

    @NotNull
    private Double load;

    @NotNull
    private Integer cages;

    @NotNull
    private Status status;

    @NotNull
    private Long farmerId;

    @NotNull
    private Long traderId;

    @NotNull
    private Long vehicleId;

    @NotNull
    private List<ItemDetailRequestDTO> itemDetails;
}
