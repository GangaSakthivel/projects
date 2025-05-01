package com.example.VehicleDocumentManagement.dto;


import com.example.VehicleDocumentManagement.model.VehicleType;

import java.util.List;

public class VehicleResponseDTO {

    private Long id;
    private String vehicleNumber;
    private String vehicleName;
    private VehicleType vehicleType;
    private Long runningKm;
    private Long nextServiceKm;
    private String notes;
    private boolean vehicleAvailability;
    private List<DocumentResponseDTO> documents;  // Nested Document DTO

    public VehicleResponseDTO() {
    }

    public VehicleResponseDTO(Long id, String vehicleNumber, String vehicleName, VehicleType vehicleType, Long runningKm, Long nextServiceKm, String notes, boolean vehicleAvailability, List<DocumentResponseDTO> documents) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.vehicleName = vehicleName;
        this.vehicleType = vehicleType;
        this.runningKm = runningKm;
        this.nextServiceKm = nextServiceKm;
        this.notes = notes;
        this.vehicleAvailability = vehicleAvailability;
        this.documents = documents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Long getRunningKm() {
        return runningKm;
    }

    public void setRunningKm(Long runningKm) {
        this.runningKm = runningKm;
    }

    public Long getNextServiceKm() {
        return nextServiceKm;
    }

    public void setNextServiceKm(Long nextServiceKm) {
        this.nextServiceKm = nextServiceKm;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isVehicleAvailability() {
        return vehicleAvailability;
    }

    public void setVehicleAvailability(boolean vehicleAvailability) {
        this.vehicleAvailability = vehicleAvailability;
    }

    public List<DocumentResponseDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentResponseDTO> documents) {
        this.documents = documents;
    }
}