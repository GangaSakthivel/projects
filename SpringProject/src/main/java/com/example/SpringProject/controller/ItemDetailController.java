package com.example.SpringProject.controller;

import com.example.SpringProject.model.ItemDetail;
import com.example.SpringProject.model.LoadWeight;
import com.example.SpringProject.service.ItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item-details")
public class ItemDetailController {

    @Autowired
    private ItemDetailService itemDetailService;

    @PostMapping("/{loadWeightId}")
    public ResponseEntity<ItemDetail> create(@PathVariable Long loadWeightId, @RequestBody ItemDetail itemDetail) {
        return ResponseEntity.ok(itemDetailService.createItemDetail(loadWeightId, itemDetail));
    }

    @GetMapping
    public ResponseEntity<List<ItemDetail>> getAll() {
        return ResponseEntity.ok(itemDetailService.getAllItemDetails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDetail> getById(@PathVariable Long id) {
        return itemDetailService.getItemDetailById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDetail> update(@PathVariable Long id, @RequestBody ItemDetail updated) {
        return ResponseEntity.ok(itemDetailService.updateItemDetail(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemDetailService.deleteItemDetail(id);
        return ResponseEntity.noContent().build();
    }



}
