package com.example.SpringProject.service;

import com.example.SpringProject.model.ItemDetail;
import com.example.SpringProject.model.LoadWeight;
import com.example.SpringProject.repository.ItemDetailRepository;
import com.example.SpringProject.repository.LoadWeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemDetailService {

    @Autowired
    private ItemDetailRepository itemDetailRepository;

    @Autowired
    private LoadWeightRepository loadWeightRepository;

    public ItemDetail createItemDetail(Long loadWeightId, ItemDetail itemDetail) {
        LoadWeight loadWeight = loadWeightRepository.findById(loadWeightId)
                .orElseThrow(() -> new RuntimeException("LoadWeight not found"));
        itemDetail.setLoadWeight(loadWeight);
        return itemDetailRepository.save(itemDetail);
    }

    public List<ItemDetail> getAllItemDetails() {
        return itemDetailRepository.findAll();
    }

    public Optional<ItemDetail> getItemDetailById(Long id) {
        return itemDetailRepository.findById(id);
    }

    public ItemDetail updateItemDetail(Long id, ItemDetail updatedDetail) {
        ItemDetail existing = itemDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemDetail not found"));

        existing.setValue(updatedDetail.getValue());
        existing.setCount(updatedDetail.getCount());
        return itemDetailRepository.save(existing);
    }

    public void deleteItemDetail(Long id) {
        itemDetailRepository.deleteById(id);
    }
}
