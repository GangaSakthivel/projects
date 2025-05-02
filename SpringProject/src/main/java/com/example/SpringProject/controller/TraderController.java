package com.example.SpringProject.controller;

import com.example.SpringProject.model.Trader;
import com.example.SpringProject.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/traders")
public class TraderController {

    @Autowired
    private TraderService traderService;

    @PostMapping
    public ResponseEntity<Trader> createTrader(@RequestBody Trader trader) {
        return ResponseEntity.ok(traderService.createTrader(trader));
    }

    @GetMapping
    public ResponseEntity<List<Trader>> getAllTraders() {
        return ResponseEntity.ok(traderService.getAllTraders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trader> getTraderById(@PathVariable Long id) {
        return ResponseEntity.ok(traderService.getTraderById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trader> updateTrader(@PathVariable Long id, @RequestBody Trader updatedTrader) {
        return ResponseEntity.ok(traderService.updateTrader(id, updatedTrader));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrader(@PathVariable Long id) {
        traderService.deleteTrader(id);
        return ResponseEntity.noContent().build();
    }
}
