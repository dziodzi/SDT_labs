package io.github.dziodzi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MetricEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private Double value;
    private String timestamp;
}
