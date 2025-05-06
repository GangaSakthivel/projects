package com.example.SpringProject.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "load_weight", uniqueConstraints = {
        @UniqueConstraint(columnNames = "number")
})
@Data
//@ToString(exclude = {"farmer", "trader", "vehicle", "itemDetails"})
//@EqualsAndHashCode(exclude = {"farmer", "trader", "vehicle", "itemDetails"})
public class LoadWeight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long number;

    @Column(nullable = false)
    private Double empty;

    @Column(nullable = false)
    private Double load;

    @Column(nullable = false)
    private Integer cages;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", nullable = false)
    private Farmer farmer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trader_id", nullable = false)
    private Trader trader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "loadWeight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemDetail> itemDetails;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

