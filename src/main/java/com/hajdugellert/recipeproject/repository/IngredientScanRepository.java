package com.hajdugellert.recipeproject.repository;

import com.hajdugellert.recipeproject.entity.IngredientScan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientScanRepository extends JpaRepository<IngredientScan, Long> {
    
}
