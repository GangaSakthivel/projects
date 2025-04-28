package com.example.VehicleDocumentManagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToMany (mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Document> documents;

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

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
