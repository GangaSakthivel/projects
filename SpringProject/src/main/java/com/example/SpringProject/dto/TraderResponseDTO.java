package com.example.SpringProject.dto;

import com.example.SpringProject.model.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TraderResponseDTO {
    private Long traderId;
    private String traderName;
    private String phoneNumber;
    private String address;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}