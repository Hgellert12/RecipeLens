package com.hajdugellert.recipeproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="ingredient_scans")
public class IngredientScan {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long id;
    private String fileName;
    private Long filesSize;
    private String fileFormat;
    @ElementCollection
    @CollectionTable(name="detected_labels", joinColumns = @JoinColumn(name="scan_id"))
    @Column(name="label")
    private List<String> detectedLabels;
    @ElementCollection
    @CollectionTable(name = "detected_ingredients", joinColumns = @JoinColumn(name = "scan_id"))
    @Column(name = "ingredient")
    private List<String> detectedIngredients;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt = LocalDate.now();





}
