package com.example.SpringProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "item_detail")
public class ItemDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;
    private int count;

    private Double totalWeight;

    @ManyToOne
    @JoinColumn(name = "load_weight_id", nullable = false)
    @JsonIgnore
    private LoadWeight loadWeight;

}
