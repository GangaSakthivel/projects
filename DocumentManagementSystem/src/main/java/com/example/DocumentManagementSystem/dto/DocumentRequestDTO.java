package com.example.DocumentManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentRequestDTO {
    private String fileName;
    private String fileType;
    private byte[] fileData;
}
