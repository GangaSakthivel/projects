package com.example.SpringProject.service;

import com.example.SpringProject.dto.ItemDetailRequestDTO;
import com.example.SpringProject.dto.ItemDetailResponseDTO;
import com.example.SpringProject.dto.LoadWeightRequestDTO;
import com.example.SpringProject.dto.LoadWeightResponseDTO;
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

    private LoadWeightResponseDTO convertToResponseDTO(LoadWeight loadWeight) {
        LoadWeightResponseDTO responseDTO = new LoadWeightResponseDTO();
        responseDTO.setId(loadWeight.getId());
        responseDTO.setNumber(loadWeight.getNumber());
        responseDTO.setEmpty(loadWeight.getEmpty());
        responseDTO.setLoad(loadWeight.getLoad());
        responseDTO.setCages(loadWeight.getCages());
        responseDTO.setStatus(loadWeight.getStatus());
        responseDTO.setFarmerId(loadWeight.getFarmer().getId());
        responseDTO.setFarmerName(loadWeight.getFarmer().getName());
        responseDTO.setFarmerPhoneNumber(loadWeight.getFarmer().getPhoneNumber());
        responseDTO.setTraderId(loadWeight.getTrader().getTraderId());
        responseDTO.setTraderName(loadWeight.getTrader().getTraderName());
        responseDTO.setTraderPhoneNumber(loadWeight.getTrader().getPhoneNumber());
        responseDTO.setVehicleId(loadWeight.getVehicle().getVehicleId());
        responseDTO.setVehicleNumber(loadWeight.getVehicle().getVehicleNumber());
        responseDTO.setCreatedAt(loadWeight.getCreatedAt());
        responseDTO.setUpdatedAt(loadWeight.getUpdatedAt());

        //load - empty = net weight
        double scaleNetWeight = loadWeight.getLoad() - loadWeight.getEmpty();
        responseDTO.setScaleNetWeight(scaleNetWeight);

        List<ItemDetailResponseDTO> itemDTOs = loadWeight.getItemDetails().stream().map(item -> {
            ItemDetailResponseDTO dto = new ItemDetailResponseDTO();
            dto.setId(item.getId());
            dto.setValue(item.getValue());
            dto.setCount(item.getCount());
            return dto;
        }).collect(Collectors.toList());
        responseDTO.setItemDetails(itemDTOs);

        double totalNetWeight = itemDTOs.stream()
                .mapToDouble(i -> i.getValue() * i.getCount())
                .sum();
        responseDTO.setTotalNetWeight(totalNetWeight);

        return responseDTO;
    }

    public LoadWeightResponseDTO createLoadWeight(LoadWeightRequestDTO dto) {
        Farmer farmer = farmerRepository.findById(dto.getFarmerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Farmer not found"));
        Trader trader = traderRepository.findById(dto.getTraderId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trader not found"));
        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found"));

        LoadWeight loadWeight = new LoadWeight();
        loadWeight.setNumber(dto.getNumber());
        loadWeight.setEmpty(dto.getEmpty());
        loadWeight.setLoad(dto.getLoad());
        loadWeight.setCages(dto.getCages());
        loadWeight.setStatus(dto.getStatus());
        loadWeight.setFarmer(farmer);
        loadWeight.setTrader(trader);
        loadWeight.setVehicle(vehicle);

        List<ItemDetail> itemDetails = dto.getItemDetails().stream().map(i -> {
            ItemDetail item = new ItemDetail();
            item.setValue(i.getValue());
            item.setCount(i.getCount());
            item.setLoadWeight(loadWeight);
            return item;
        }).collect(Collectors.toList());

        loadWeight.setItemDetails(itemDetails);

        LoadWeight saved = loadWeightRepository.save(loadWeight);
        return convertToResponseDTO(saved);
    }

    public List<LoadWeightResponseDTO> getAllLoadWeights() {
        return loadWeightRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public LoadWeightResponseDTO getLoadWeightById(Long id) {
        LoadWeight lw = loadWeightRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LoadWeight not found with id: " + id));
        return convertToResponseDTO(lw);
    }

    public LoadWeightResponseDTO updateLoadWeight(Long id, LoadWeightRequestDTO dto) {
        LoadWeight loadWeight = loadWeightRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LoadWeight not found"));

        Farmer farmer = farmerRepository.findById(dto.getFarmerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Farmer not found"));
        Trader trader = traderRepository.findById(dto.getTraderId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trader not found"));
        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found"));

        loadWeight.setNumber(dto.getNumber());
        loadWeight.setEmpty(dto.getEmpty());
        loadWeight.setLoad(dto.getLoad());
        loadWeight.setCages(dto.getCages());
        loadWeight.setStatus(dto.getStatus());
        loadWeight.setFarmer(farmer);
        loadWeight.setTrader(trader);
        loadWeight.setVehicle(vehicle);

        loadWeight.getItemDetails().clear();
        List<ItemDetail> updatedItems = dto.getItemDetails().stream().map(i -> {
            ItemDetail item = new ItemDetail();
            item.setValue(i.getValue());
            item.setCount(i.getCount());
            item.setLoadWeight(loadWeight);
            return item;
        }).collect(Collectors.toList());

        loadWeight.getItemDetails().addAll(updatedItems);

        LoadWeight updated = loadWeightRepository.save(loadWeight);
        return convertToResponseDTO(updated);
    }

    public void deleteLoadWeight(Long id) {
        if (!loadWeightRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "LoadWeight not found with id: " + id);
        }
        loadWeightRepository.deleteById(id);
    }

    public List<LoadWeightResponseDTO> getLoadWeightsByVehicleNumber(String vehicleNumber) {
        List<LoadWeight> loadWeights = loadWeightRepository.findByVehicleVehicleNumber(vehicleNumber);
        if (loadWeights == null || loadWeights.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data found for vehicle number: " + vehicleNumber);
        }
        return loadWeights.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
}
