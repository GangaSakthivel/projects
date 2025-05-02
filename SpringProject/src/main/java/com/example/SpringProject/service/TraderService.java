package com.example.SpringProject.service;

import com.example.SpringProject.model.Trader;
import com.example.SpringProject.repository.TraderRepository;
import com.example.SpringProject.exceptions.ResourceNotFoundException;  // Custom exception
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraderService {

    @Autowired
    private TraderRepository traderRepository;

    public Trader createTrader(Trader trader) {
        return traderRepository.save(trader);
    }

    public List<Trader> getAllTraders() {
        return traderRepository.findAll();
    }

    public Trader getTraderById(Long id) {
        return traderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trader not found with id " + id));
    }

    public Trader updateTrader(Long id, Trader updatedTrader) {
        Trader existingTrader = traderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trader not found with id " + id));

        updatedTrader.setTraderId(id);
        return traderRepository.save(updatedTrader);
    }

    public void deleteTrader(Long id) {
        Trader existingTrader = traderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trader not found with id " + id));
        traderRepository.delete(existingTrader);
    }
}
