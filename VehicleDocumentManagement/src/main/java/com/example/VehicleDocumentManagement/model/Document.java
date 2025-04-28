package com.example.VehicleDocumentManagement.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private byte[] vehiclePhoto;

    @Lob
    @ElementCollection
    private List<byte[]> documents;

    @ManyToOne
    @JoinColumn(name = "vehicle_id") //column name in document that references primary key in vehicles
    private Vehicle vehicle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getVehiclePhoto() {
        return vehiclePhoto;
    }

    public void setVehiclePhoto(byte[] vehiclePhoto) {
        this.vehiclePhoto = vehiclePhoto;
    }

    public List<byte[]> getDocuments() {
        return documents;
    }

    public void setDocuments(List<byte[]> documents) {
        this.documents = documents;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
