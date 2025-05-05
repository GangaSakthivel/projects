package com.example.SpringProject.controller;

import com.example.SpringProject.dto.ItemDetailRequestDTO;
import com.example.SpringProject.dto.ItemDetailResponseDTO;
import com.example.SpringProject.service.ItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/item-details")
public class ItemDetailController {

    @Autowired
    private ItemDetailService itemDetailService;

    @PostMapping("/{loadWeightId}")
    public ResponseEntity<ItemDetailResponseDTO> createItemDetail(
            @PathVariable Long loadWeightId,
            @Valid @RequestBody ItemDetailRequestDTO itemDetailRequestDTO) {
        ItemDetailResponseDTO createdItemDetail = itemDetailService.createItemDetail(itemDetailRequestDTO, loadWeightId);
        return new ResponseEntity<>(createdItemDetail, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ItemDetailResponseDTO>> getAllItemDetails() {
        List<ItemDetailResponseDTO> itemDetails = itemDetailService.getAllItemDetails();
        return new ResponseEntity<>(itemDetails, HttpStatus.OK);
    }

    // GET: Get an ItemDetail by ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemDetailResponseDTO> getItemDetailById(@PathVariable Long id) {
        try {
            ItemDetailResponseDTO itemDetail = itemDetailService.getItemDetailById(id);
            return new ResponseEntity<>(itemDetail, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            // Exception is already handled in the service; just return the ResponseEntity
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDetailResponseDTO> updateItemDetail(@PathVariable Long id, @Valid @RequestBody ItemDetailRequestDTO itemDetailRequestDTO) {
        try {
            ItemDetailResponseDTO updatedItemDetail = itemDetailService.updateItemDetail(id, itemDetailRequestDTO);
            return new ResponseEntity<>(updatedItemDetail, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemDetail(@PathVariable Long id) {
        try {
            itemDetailService.deleteItemDetail(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

