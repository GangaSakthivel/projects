package com.example.SpringProject.dto;

import com.example.SpringProject.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class FarmerResponseDTO {

        private Long id;
        private String name;
        private String phoneNumber;
        private String address;
        private Status status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

}
