package com.example.SpringProject.dto;

import com.example.SpringProject.model.Status;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoadWeightRequestDTO {

    private Long number;
    private Double empty;
    private Double load;
    private Status status;
    @NotNull
    @Column(unique = true)
    private Long vehicleId;
    @NotNull
    private Long farmerId;
    @NotNull
    private Long traderId;

    public LoadWeightRequestDTO() {
    }

    public LoadWeightRequestDTO(Long number, Double empty, Double load, Status status, Long vehicleId, Long farmerId, Long traderId) {
        this.number = number;
        this.empty = empty;
        this.load = load;
        this.status = status;
        this.vehicleId = vehicleId;
        this.farmerId = farmerId;
        this.traderId = traderId;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
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
}

