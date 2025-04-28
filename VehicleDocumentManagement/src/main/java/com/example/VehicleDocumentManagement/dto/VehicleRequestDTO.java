package com.example.VehicleDocumentManagement.dto;

import com.example.VehicleDocumentManagement.model.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VehicleRequestDTO {

    @Column(unique = true)
    private String vehicleNumber;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    @NotNull
    private Long runningKm;
    @NotNull
    private Long nextServiceKm;
    @NotNull
    private Long fuelTankCapacity;
    private boolean vehicleAvailability;
    @NotBlank
    private String notes;

    public VehicleRequestDTO() {
    }

    public VehicleRequestDTO(Long id, String vehicleNumber, VehicleType vehicleType, Long runningKm, Long nextServiceKm, Long fuelTankCapacity, boolean vehicleAvailability, String notes) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.runningKm = runningKm;
        this.nextServiceKm = nextServiceKm;
        this.fuelTankCapacity = fuelTankCapacity;
        this.vehicleAvailability = vehicleAvailability;
        this.notes = notes;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
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

    public Long getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(Long fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public boolean isVehicleAvailability() {
        return vehicleAvailability;
    }

    public void setVehicleAvailability(boolean vehicleAvailability) {
        this.vehicleAvailability = vehicleAvailability;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
