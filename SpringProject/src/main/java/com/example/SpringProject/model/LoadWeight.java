package com.example.SpringProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "load_weight")
public class LoadWeight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long number;

    @Column(nullable = false)
    private Double empty;

    @Column(nullable = false)
    private Double load;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;


    @ManyToOne
    @JoinColumn(name = "farmer_id")
    @JsonIgnore
    private Farmer farmer;


    @ManyToOne
    @JoinColumn(name = "trader_id", nullable = false)
    private Trader trader;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @OneToMany(mappedBy = "loadWeight", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ItemDetail> itemDetails = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Double getNetWeight() {
        return load - empty;
    }

    public LoadWeight() {
    }

    public LoadWeight(Long id, Long number, Double empty, Double load, Status status, Farmer farmer, Trader trader, Vehicle vehicle, LocalDateTime createdAt, LocalDateTime updatedAt, List<ItemDetail> itemDetails) {
        this.id = id;
        this.number = number;
        this.empty = empty;
        this.load = load;
        this.status = status;
        this.farmer = farmer;
        this.trader = trader;
        this.vehicle = vehicle;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.itemDetails = itemDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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
}
