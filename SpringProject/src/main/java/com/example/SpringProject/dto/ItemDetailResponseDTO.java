package com.example.SpringProject.dto;

import lombok.Data;

@Data
public class ItemDetailResponseDTO {
    private Long id;
    private Double value; //in kg (1.5 kg for one item )
    private int count;//how many item in each cage
}

