package com.example.SpringProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoadWeightResponseDTO {
    private String vehicleNumber;
    private String farmerName;
    private String traderName;
    private Double empty;
    private Double load;
    private Double netWeight;
    private String status;

}
