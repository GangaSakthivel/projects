package com.example.SpringProject.dto;

import com.example.SpringProject.model.Status;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class LoadWeightRequestDTO {

    private String number;
    private Double empty;
    private Double load;
    private Status status;
    private Long vehicleId;
    private Long farmerId;
    private Long traderId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LoadWeightRequestDTO() {
    }

    public LoadWeightRequestDTO(String number, Double empty, Double load, Status status, Long vehicleId, Long farmerId, Long traderId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.number = number;
        this.empty = empty;
        this.load = load;
        this.status = status;
        this.vehicleId = vehicleId;
        this.farmerId = farmerId;
        this.traderId = traderId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getEmpty() {
        return empty;
    }

    public void setEmpty(Double empty) {
        this.empty = empty;
    }

    public Double getLoad() {
        return load;
    }

    public void setLoad(Double load) {
        this.load = load;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }

    public Long getTraderId() {
        return traderId;
    }

    public void setTraderId(Long traderId) {
        this.traderId = traderId;
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

