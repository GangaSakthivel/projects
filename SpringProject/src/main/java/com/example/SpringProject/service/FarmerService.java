package com.example.SpringProject.service;

import com.example.SpringProject.model.Farmer;
import com.example.SpringProject.repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    public Farmer createFarmer(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll();
    }

    public Optional<Farmer> getFarmerById(Long id) {
        return farmerRepository.findById(id);
    }

    public Farmer updateFarmer(Long id, Farmer updatedFarmer) {
        Optional<Farmer> existingFarmer = farmerRepository.findById(id);
        if (existingFarmer.isPresent()) {
            updatedFarmer.setId(id);
            return farmerRepository.save(updatedFarmer);
        }
        return null;
    }

    public void deleteFarmer(Long id) {
        farmerRepository.deleteById(id);
    }
}
