package com.example.SpringProject.dto;

import com.example.SpringProject.model.Status; // Assuming Status is in this package
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LoadWeightResponseDTO {
    private Long id;
    private Long number;
    private Double empty;
    private Double load;
    private Integer cages;
    private Status status;
    private Long farmerId;
    private Long traderId;
    private Long vehicleId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ItemDetailResponseDTO> itemDetails;
}


