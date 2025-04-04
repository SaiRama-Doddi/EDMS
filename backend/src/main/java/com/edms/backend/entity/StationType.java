package com.edms.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "station_type")
@Getter
@Setter
public class StationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StationEnum type;

    @OneToOne
    @JoinColumn(name = "station_id", unique = true, nullable = false)
    private StationCode station;


    public StationEnum getType() { // ✅ Ensure this getter exists
        return type;
    }

    // Getters and Setters
}



