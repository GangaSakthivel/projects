package com.example.VehicleDocumentManagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_number", unique = true, nullable = false)
    private String vehicleNumber;

    @NotBlank
    @Size(max = 100)
    @Column(name = "vehicle_name", nullable = false)
    private String vehicleName;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "vehicle_type", nullable = false)
    private VehicleType vehicleType;

    @NotNull
    @Min(0)
    @Column(name = "running_km", nullable = false)
    private Long runningKm;

    @NotNull
    @Min(0)
    @Column(name = "next_service_km", nullable = false)
    private Long nextServiceKm;

    @Size(max = 255)
    private String notes;

    @Column(name = "vehicle_availability", nullable = false)
    private boolean vehicleAvailability;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents = new ArrayList<>();

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

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}