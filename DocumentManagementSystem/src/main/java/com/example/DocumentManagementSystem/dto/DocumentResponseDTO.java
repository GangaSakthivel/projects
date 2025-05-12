package com.example.DocumentManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentResponseDTO {
    private Long id;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private LocalDateTime uploadTime;
}
