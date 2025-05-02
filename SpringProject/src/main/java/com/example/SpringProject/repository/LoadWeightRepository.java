package com.example.SpringProject.repository;

import com.example.SpringProject.model.LoadWeight;
import com.example.SpringProject.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoadWeightRepository extends JpaRepository<LoadWeight, Long> {
    Optional<LoadWeight> findByVehicle(Vehicle vehicle);

}
