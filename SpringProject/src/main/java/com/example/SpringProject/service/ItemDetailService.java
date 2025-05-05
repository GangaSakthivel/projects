package com.example.SpringProject.service;

import com.example.SpringProject.dto.ItemDetailRequestDTO;
import com.example.SpringProject.dto.ItemDetailResponseDTO;
import com.example.SpringProject.model.ItemDetail;
import com.example.SpringProject.model.LoadWeight;
import com.example.SpringProject.repository.ItemDetailRepository;
import com.example.SpringProject.repository.LoadWeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemDetailService {

    @Autowired
    private ItemDetailRepository itemDetailRepository;
    @Autowired
    private LoadWeightRepository loadWeightRepository;

    // Helper method to convert ItemDetail entity to ItemDetailResponseDTO
    private ItemDetailResponseDTO convertToResponseDTO(ItemDetail itemDetail) {
        ItemDetailResponseDTO responseDTO = new ItemDetailResponseDTO();
        responseDTO.setId(itemDetail.getId());
        responseDTO.setValue(itemDetail.getValue());
        responseDTO.setCount(itemDetail.getCount());
        return responseDTO;
    }

    // POST: Create a new ItemDetail
    public ItemDetailResponseDTO createItemDetail(ItemDetailRequestDTO itemDetailRequestDTO, Long loadWeightId) { // Added loadWeightId
        // Fetch the LoadWeight, handle exceptions.
        LoadWeight loadWeight = loadWeightRepository.findById(loadWeightId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LoadWeight not found with id: " + loadWeightId));

        ItemDetail itemDetail = new ItemDetail();
        itemDetail.setValue(itemDetailRequestDTO.getValue());
        itemDetail.setCount(itemDetailRequestDTO.getCount());
        itemDetail.setLoadWeight(loadWeight); // Associate with the LoadWeight

        ItemDetail savedItemDetail = itemDetailRepository.save(itemDetail);
        return convertToResponseDTO(savedItemDetail);
    }

    // GET: Get all ItemDetails
    public List<ItemDetailResponseDTO> getAllItemDetails() {
        List<ItemDetail> itemDetails = itemDetailRepository.findAll();
        return itemDetails.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // GET: Get an ItemDetail by ID
    public ItemDetailResponseDTO getItemDetailById(Long id) {
        Optional<ItemDetail> itemDetail = itemDetailRepository.findById(id);
        if (itemDetail.isPresent()) {
            return convertToResponseDTO(itemDetail.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemDetail not found with id: " + id);
        }
    }

    // PUT: Update an ItemDetail by ID
    public ItemDetailResponseDTO updateItemDetail(Long id, ItemDetailRequestDTO itemDetailRequestDTO) {
        Optional<ItemDetail> existingItemDetail = itemDetailRepository.findById(id);
        if (existingItemDetail.isPresent()) {

            ItemDetail itemDetailToUpdate = existingItemDetail.get();
            itemDetailToUpdate.setValue(itemDetailRequestDTO.getValue());
            itemDetailToUpdate.setCount(itemDetailRequestDTO.getCount());


            ItemDetail updatedItemDetail = itemDetailRepository.save(itemDetailToUpdate);
            return convertToResponseDTO(updatedItemDetail);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemDetail not found with id: " + id);
        }
    }

    // DELETE: Delete an ItemDetail by ID
    public void deleteItemDetail(Long id) {
        Optional<ItemDetail> itemDetail = itemDetailRepository.findById(id);
        if (itemDetail.isPresent()) {
            itemDetailRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemDetail not found with id: " + id);
        }
    }
}