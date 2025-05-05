package com.example.SpringProject.dto;

import com.example.SpringProject.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class FarmerResponseDTO {

        private Long id;
        private String name;
        private String phoneNumber;
        private String address;
        private Status status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public FarmerResponseDTO() {
        }

        public FarmerResponseDTO(Long id, String name, String phoneNumber, String address, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
                this.id = id;
                this.name = name;
                this.phoneNumber = phoneNumber;
                this.address = address;
                this.status = status;
                this.createdAt = createdAt;
                this.updatedAt = updatedAt;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getPhoneNumber() {
                return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public Status getStatus() {
                return status;
        }

        public void setStatus(Status status) {
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
}
