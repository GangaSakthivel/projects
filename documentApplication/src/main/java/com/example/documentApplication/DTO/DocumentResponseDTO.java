package com.example.documentApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String fileName;
    private String fileType;
    private LocalDateTime uploadedAt;
}
