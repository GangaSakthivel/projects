package com.example.SpringProject.repository;

import com.example.SpringProject.model.ItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDetailRepository extends JpaRepository<ItemDetail, Long> {
}
