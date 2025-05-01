package com.example.VehicleDocumentManagement.dto;

public class DocumentResponseDTO {
    private Long id;
    private String documentType;   // Optional: if you want to differentiate document types (e.g., insurance, registration)
    private String fileName;       // Optional: name of the file
    private String fileUrl;

    public DocumentResponseDTO() {
    }

    public DocumentResponseDTO(Long id, String documentType, String fileName, String fileUrl) {
        this.id = id;
        this.documentType = documentType;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}