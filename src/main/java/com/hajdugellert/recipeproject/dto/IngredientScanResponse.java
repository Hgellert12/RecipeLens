package com.hajdugellert.recipeproject.dto;

import java.time.LocalDateTime;
import java.util.List;

public record IngredientScanResponse(
        Long id,
        List<String> detectedingredients,
        List<String> detectedLabels,
        String fileName,
        LocalDateTime createdAt







)


{

}
