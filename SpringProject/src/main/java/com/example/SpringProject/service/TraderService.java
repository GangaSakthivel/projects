package com.example.SpringProject.service;

import com.example.SpringProject.dto.TraderRequestDTO;
import com.example.SpringProject.dto.TraderResponseDTO;
import com.example.SpringProject.model.Trader;
import com.example.SpringProject.repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TraderService {

    @Autowired
    private TraderRepository traderRepository;

    // Helper method to convert Trader entity to TraderResponseDTO
    private TraderResponseDTO convertToResponseDTO(Trader trader) {
        TraderResponseDTO responseDTO = new TraderResponseDTO();
        responseDTO.setTraderId(trader.getTraderId());
        responseDTO.setTraderName(trader.getTraderName());
        responseDTO.setPhoneNumber(trader.getPhoneNumber());
        responseDTO.setAddress(trader.getAddress());
        responseDTO.setStatus(trader.getStatus());
        responseDTO.setCreatedAt(trader.getCreatedAt());
        responseDTO.setUpdatedAt(trader.getUpdatedAt());
        return responseDTO;
    }

    // POST: Create a new Trader
    public TraderResponseDTO createTrader(TraderRequestDTO traderRequestDTO) {
        Trader trader = new Trader();
        trader.setTraderName(traderRequestDTO.getTraderName());
        trader.setPhoneNumber(traderRequestDTO.getPhoneNumber());
        trader.setAddress(traderRequestDTO.getAddress());
        trader.setStatus(traderRequestDTO.getStatus());
        // createdAt and updatedAt will be handled by @PrePersist and @PreUpdate in the Entity
        Trader savedTrader = traderRepository.save(trader);
        return convertToResponseDTO(savedTrader);
    }

    // GET: Get all Traders
    public List<TraderResponseDTO> getAllTraders() {
        List<Trader> traders = traderRepository.findAll();
        return traders.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // GET: Get a Trader by ID
    public TraderResponseDTO getTraderById(Long id) {
        Optional<Trader> trader = traderRepository.findById(id);
        if (trader.isPresent()) {
            return convertToResponseDTO(trader.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trader not found with id: " + id);
        }
    }

    // PUT: Update a Trader by ID
    public TraderResponseDTO updateTrader(Long id, TraderRequestDTO traderRequestDTO) {
        Optional<Trader> existingTrader = traderRepository.findById(id);
        if (existingTrader.isPresent()) {
            Trader traderToUpdate = existingTrader.get();
            traderToUpdate.setTraderName(traderRequestDTO.getTraderName());
            traderToUpdate.setPhoneNumber(traderRequestDTO.getPhoneNumber());
            traderToUpdate.setAddress(traderRequestDTO.getAddress());
            traderToUpdate.setStatus(traderRequestDTO.getStatus());
            // updatedAt will be handled by @PreUpdate in the Entity
            Trader updatedTrader = traderRepository.save(traderToUpdate);
            return convertToResponseDTO(updatedTrader);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trader not found with id: " + id);
        }
    }

    // DELETE: Delete a Trader by ID
    public void deleteTrader(Long id) {
        Optional<Trader> trader = traderRepository.findById(id);
        if (trader.isPresent()) {
            traderRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trader not found with id: " + id);
        }
    }
}