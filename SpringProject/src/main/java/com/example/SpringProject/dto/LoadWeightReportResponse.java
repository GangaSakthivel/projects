package com.example.SpringProject.dto;

import com.example.SpringProject.model.ItemDetail;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;
import java.util.List;

public class LoadWeightReportResponse {
    private String vehicleNumber;
    private String vehicleName;
    private String farmerName;
    private String traderName;
    private Double emptyWeight;
    private Double loadWeight;
    private Double netWeight;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ItemDetail> itemDetails;

    public LoadWeightReportResponse() {
    }

    public LoadWeightReportResponse(String vehicleNumber, String vehicleName, String farmerName, String traderName, Double emptyWeight, Double loadWeight, Double netWeight, String status, LocalDateTime createdAt, LocalDateTime updatedAt, List<ItemDetail> itemDetails) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleName = vehicleName;
        this.farmerName = farmerName;
        this.traderName = traderName;
        this.emptyWeight = emptyWeight;
        this.loadWeight = loadWeight;
        this.netWeight = netWeight;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.itemDetails = itemDetails;
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

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public Double getEmptyWeight() {
        return emptyWeight;
    }

    public void setEmptyWeight(Double emptyWeight) {
        this.emptyWeight = emptyWeight;
    }

    public Double getLoadWeight() {
        return loadWeight;
    }

    public void setLoadWeight(Double loadWeight) {
        this.loadWeight = loadWeight;
    }

    public Double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ItemDetail> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(List<ItemDetail> itemDetails) {
        this.itemDetails = itemDetails;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
