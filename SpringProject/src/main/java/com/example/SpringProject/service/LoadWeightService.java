package com.example.SpringProject.service;

import com.example.SpringProject.dto.LoadWeightReportResponse;
import com.example.SpringProject.dto.LoadWeightRequestDTO;
import com.example.SpringProject.dto.LoadWeightResponseDTO;
import com.example.SpringProject.exceptions.ResourceNotFoundException;
import com.example.SpringProject.model.Farmer;
import com.example.SpringProject.model.LoadWeight;
import com.example.SpringProject.model.Trader;
import com.example.SpringProject.model.Vehicle;
import com.example.SpringProject.repository.FarmerRepository;
import com.example.SpringProject.repository.LoadWeightRepository;
import com.example.SpringProject.repository.TraderRepository;
import com.example.SpringProject.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class LoadWeightService {

    @Autowired
    private LoadWeightRepository loadWeightRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private TraderRepository traderRepository;

    public ResponseEntity<LoadWeightResponseDTO> createLoadWeight(LoadWeightRequestDTO loadWeightRequestDTO) {
        // Fetch associated entities
        Vehicle vehicle = vehicleRepository.findById(loadWeightRequestDTO.getVehicleId()).orElseThrow(() -> new RuntimeException("Vehicle not found"));
        Farmer farmer = farmerRepository.findById(loadWeightRequestDTO.getFarmerId()).orElseThrow(() -> new RuntimeException("Farmer not found"));
        Trader trader = traderRepository.findById(loadWeightRequestDTO.getTraderId()).orElseThrow(() -> new RuntimeException("Trader not found"));

        LoadWeight loadWeight = new LoadWeight();
        loadWeight.setNumber(loadWeightRequestDTO.getNumber());
        loadWeight.setEmpty(loadWeightRequestDTO.getEmpty());
        loadWeight.setLoad(loadWeightRequestDTO.getLoad());
        loadWeight.setStatus(loadWeightRequestDTO.getStatus());
        loadWeight.setVehicle(vehicle);
        loadWeight.setFarmer(farmer);
        loadWeight.setTrader(trader);

        LoadWeight savedLoadWeight = loadWeightRepository.save(loadWeight);

        LoadWeightResponseDTO responseDTO = new LoadWeightResponseDTO();
        responseDTO.setVehicleNumber(savedLoadWeight.getVehicle().getVehicleNumber().toString());
        responseDTO.setFarmerName(savedLoadWeight.getFarmer().getName());
        responseDTO.setTraderName(savedLoadWeight.getTrader().getTraderName());
        responseDTO.setEmptyWeight(savedLoadWeight.getEmpty());
        responseDTO.setLoadWeight(savedLoadWeight.getLoad());
        responseDTO.setNetWeight(savedLoadWeight.getLoad() - savedLoadWeight.getEmpty());
        responseDTO.setStatus(savedLoadWeight.getStatus().toString());
        responseDTO.setCreatedAt(savedLoadWeight.getCreatedAt());
        responseDTO.setUpdatedAt(savedLoadWeight.getUpdatedAt());

        return ResponseEntity.ok(responseDTO);
    }


    public LoadWeightResponseDTO getLoadWeightById(Long id) {
        LoadWeight lw = loadWeightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LoadWeight not found with id: " + id));

        return mapToResponseDTO(lw);
    }

    public void deleteLoadWeight(Long id) {
        LoadWeight lw = loadWeightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LoadWeight not found with id: " + id));
        loadWeightRepository.delete(lw);
    }

    public LoadWeightResponseDTO updateLoadWeight(Long id, LoadWeightRequestDTO dto) {
        LoadWeight lw = loadWeightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LoadWeight not found with id: " + id));

        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
        Farmer farmer = farmerRepository.findById(dto.getFarmerId())
                .orElseThrow(() -> new ResourceNotFoundException("Farmer not found"));
        Trader trader = traderRepository.findById(dto.getTraderId())
                .orElseThrow(() -> new ResourceNotFoundException("Trader not found"));

        lw.setVehicle(vehicle);
        lw.setFarmer(farmer);
        lw.setTrader(trader);
        lw.setNumber(dto.getNumber());
        lw.setEmpty(dto.getEmpty());
        lw.setLoad(dto.getLoad());
        lw.setStatus(dto.getStatus());


        LoadWeight updated = loadWeightRepository.save(lw);
        return mapToResponseDTO(updated);
    }

    private LoadWeightResponseDTO mapToResponseDTO(LoadWeight lw) {
        Double netWeight = lw.getLoad() - lw.getEmpty();

        return new LoadWeightResponseDTO(
                lw.getVehicle().getVehicleNumber().toString(),
                lw.getFarmer().getName(),
                lw.getTrader().getTraderName(),
                lw.getEmpty(),
                lw.getLoad(),
                netWeight,
                lw.getStatus().name(),
                lw.getCreatedAt(),
                lw.getUpdatedAt()
        );
    }

    public LoadWeight saveLoadWeight(LoadWeight loadWeight) {
        // Persist LoadWeight (and cascading ItemDetails)
        return loadWeightRepository.save(loadWeight);
    }


}
