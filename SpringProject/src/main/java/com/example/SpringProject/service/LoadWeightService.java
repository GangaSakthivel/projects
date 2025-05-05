
package com.example.SpringProject.service;

import com.example.SpringProject.dto.LoadWeightRequestDTO;
import com.example.SpringProject.dto.LoadWeightResponseDTO;
import com.example.SpringProject.dto.ItemDetailResponseDTO;
import com.example.SpringProject.model.*;
import com.example.SpringProject.repository.FarmerRepository;
import com.example.SpringProject.repository.TraderRepository;
import com.example.SpringProject.repository.VehicleRepository;
import com.example.SpringProject.repository.LoadWeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoadWeightService {

    @Autowired
    private LoadWeightRepository loadWeightRepository;
    @Autowired
    private FarmerRepository farmerRepository;
    @Autowired
    private TraderRepository traderRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    // Helper method to convert LoadWeight entity to LoadWeightResponseDTO
    private LoadWeightResponseDTO convertToResponseDTO(LoadWeight loadWeight) {
        LoadWeightResponseDTO responseDTO = new LoadWeightResponseDTO();
        responseDTO.setId(loadWeight.getId());
        responseDTO.setNumber(loadWeight.getNumber());
        responseDTO.setEmpty(loadWeight.getEmpty());
        responseDTO.setLoad(loadWeight.getLoad());
        responseDTO.setCages(loadWeight.getCages());
        responseDTO.setStatus(loadWeight.getStatus());
        if (loadWeight.getFarmer() != null) {
            responseDTO.setFarmerId(loadWeight.getFarmer().getId());
            responseDTO.setFarmerName(loadWeight.getFarmer().getName());
            responseDTO.setFarmerPhoneNumber(loadWeight.getFarmer().getPhoneNumber());
        }
        if (loadWeight.getTrader() != null) {
            responseDTO.setTraderId(loadWeight.getTrader().getTraderId());
            responseDTO.setTraderName(loadWeight.getTrader().getTraderName());
            responseDTO.setTraderPhoneNumber(loadWeight.getTrader().getPhoneNumber());
        }
        if (loadWeight.getVehicle() != null) {
            responseDTO.setVehicleId(loadWeight.getVehicle().getVehicleId());
            responseDTO.setVehicleNumber(loadWeight.getVehicle().getVehicleNumber());
        }
        responseDTO.setCreatedAt(loadWeight.getCreatedAt());
        responseDTO.setUpdatedAt(loadWeight.getUpdatedAt());
        responseDTO.setNetWeight(loadWeight.getLoad() - loadWeight.getEmpty());
        responseDTO.setItemDetails(loadWeight.getItemDetails().stream()
                .map(itemDetail -> {
                    ItemDetailResponseDTO itemDetailDTO = new ItemDetailResponseDTO();
                    itemDetailDTO.setId(itemDetail.getId());
                    itemDetailDTO.setValue(itemDetail.getValue());
                    itemDetailDTO.setCount(itemDetailDTO.getCount());
                    return itemDetailDTO;
                })
                .collect(Collectors.toList()));
        return responseDTO;
    }

    // POST: Create a new LoadWeight
    public LoadWeightResponseDTO createLoadWeight(LoadWeightRequestDTO loadWeightRequestDTO) {
        // Fetch related entities, handle exceptions if not found.
        Farmer farmer = farmerRepository.findById(loadWeightRequestDTO.getFarmerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Farmer not found with id: " + loadWeightRequestDTO.getFarmerId()));
        Trader trader = traderRepository.findById(loadWeightRequestDTO.getTraderId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trader not found with id: " + loadWeightRequestDTO.getTraderId()));
        Vehicle vehicle = vehicleRepository.findById(loadWeightRequestDTO.getVehicleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with id: " + loadWeightRequestDTO.getVehicleId()));

        LoadWeight loadWeight = new LoadWeight();
        loadWeight.setNumber(loadWeightRequestDTO.getNumber());
        loadWeight.setEmpty(loadWeightRequestDTO.getEmpty());
        loadWeight.setLoad(loadWeightRequestDTO.getLoad());
        loadWeight.setCages(loadWeightRequestDTO.getCages());
        loadWeight.setStatus(loadWeightRequestDTO.getStatus());
        loadWeight.setFarmer(farmer);
        loadWeight.setTrader(trader);
        loadWeight.setVehicle(vehicle);

        // Handle ItemDetails
        List<ItemDetail> itemDetails = loadWeightRequestDTO.getItemDetails().stream()
                .map(itemDetailDTO -> {
                    ItemDetail itemDetail = new ItemDetail();
                    itemDetail.setValue(itemDetailDTO.getValue());
                    itemDetail.setCount(itemDetailDTO.getCount());
                    itemDetail.setLoadWeight(loadWeight); // Set the LoadWeight for each ItemDetail
                    return itemDetail;
                })
                .collect(Collectors.toList());
        loadWeight.setItemDetails(itemDetails);

        LoadWeight savedLoadWeight = loadWeightRepository.save(loadWeight);
        return convertToResponseDTO(savedLoadWeight);
    }

    // GET: Get all LoadWeights
    public List<LoadWeightResponseDTO> getAllLoadWeights() {
        List<LoadWeight> loadWeights = loadWeightRepository.findAll();
        return loadWeights.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // GET: Get a LoadWeight by ID
    public LoadWeightResponseDTO getLoadWeightById(Long id) {
        Optional<LoadWeight> loadWeight = loadWeightRepository.findById(id);
        if (loadWeight.isPresent()) {
            return convertToResponseDTO(loadWeight.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "LoadWeight not found with id: " + id);
        }
    }

    // PUT: Update an existing LoadWeight
    public LoadWeightResponseDTO updateLoadWeight(Long id, LoadWeightRequestDTO loadWeightRequestDTO) {
        Optional<LoadWeight> existingLoadWeight = loadWeightRepository.findById(id);
        if (existingLoadWeight.isPresent()) {
            // Fetch related entities, handle exceptions if not found.
            Farmer farmer = farmerRepository.findById(loadWeightRequestDTO.getFarmerId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Farmer not found with id: " + loadWeightRequestDTO.getFarmerId()));
            Trader trader = traderRepository.findById(loadWeightRequestDTO.getTraderId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trader not found with id: " + loadWeightRequestDTO.getTraderId()));
            Vehicle vehicle = vehicleRepository.findById(loadWeightRequestDTO.getVehicleId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with id: " + loadWeightRequestDTO.getVehicleId()));

            LoadWeight loadWeightToUpdate = existingLoadWeight.get();
            loadWeightToUpdate.setNumber(loadWeightRequestDTO.getNumber());
            loadWeightToUpdate.setEmpty(loadWeightRequestDTO.getEmpty());
            loadWeightToUpdate.setLoad(loadWeightRequestDTO.getLoad());
            loadWeightToUpdate.setCages(loadWeightRequestDTO.getCages());
            loadWeightToUpdate.setStatus(loadWeightRequestDTO.getStatus());
            loadWeightToUpdate.setFarmer(farmer);
            loadWeightToUpdate.setTrader(trader);
            loadWeightToUpdate.setVehicle(vehicle);

            // Handle ItemDetails
            List<ItemDetail> newItemDetails = loadWeightRequestDTO.getItemDetails().stream()
                    .map(itemDetailDTO -> {
                        ItemDetail itemDetail = new ItemDetail();
                        itemDetail.setValue(itemDetailDTO.getValue());
                        itemDetail.setCount(itemDetailDTO.getCount());
                        itemDetail.setLoadWeight(loadWeightToUpdate);
                        return itemDetail;
                    })
                    .collect(Collectors.toList());
            loadWeightToUpdate.setItemDetails(newItemDetails);

            LoadWeight updatedLoadWeight = loadWeightRepository.save(loadWeightToUpdate);
            return convertToResponseDTO(updatedLoadWeight);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "LoadWeight not found with id: " + id);
        }
    }

    // DELETE: Delete a LoadWeight by ID
    public void deleteLoadWeight(Long id) {
        Optional<LoadWeight> loadWeight = loadWeightRepository.findById(id);
        if (loadWeight.isPresent()) {
            loadWeightRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "LoadWeight not found with id: " + id);
        }
    }
}

