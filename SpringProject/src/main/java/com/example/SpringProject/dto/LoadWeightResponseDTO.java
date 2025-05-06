package com.example.SpringProject.dto;

import com.example.SpringProject.model.Status;
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
    private String farmerName;
    private String farmerPhoneNumber;
    private Long traderId;
    private String traderName;
    private String traderPhoneNumber;
    private Long vehicleId;
    private String vehicleNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private double TotalNetWeight;
    private double scaleNetWeight;
    private List<ItemDetailResponseDTO> itemDetails;
}


