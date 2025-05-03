package com.example.SpringProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "item_detail")
public class ItemDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value;
    private int count;

    @ManyToOne
    @JoinColumn(name = "load_weight_id", nullable = false)
    @JsonIgnore
    private LoadWeight loadWeight;

    public ItemDetail() {
    }

    public ItemDetail(Long id, int value, int count, LoadWeight loadWeight) {
        this.id = id;
        this.value = value;
        this.count = count;
        this.loadWeight = loadWeight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LoadWeight getLoadWeight() {
        return loadWeight;
    }

    public void setLoadWeight(LoadWeight loadWeight) {
        this.loadWeight = loadWeight;
    }
}

