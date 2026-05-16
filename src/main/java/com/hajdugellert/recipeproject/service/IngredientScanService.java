package com.hajdugellert.recipeproject.service;

import com.hajdugellert.recipeproject.dto.IngredientScanResponse;
import com.hajdugellert.recipeproject.entity.IngredientScan;
import com.hajdugellert.recipeproject.mapper.IngredientScanMapper;
import com.hajdugellert.recipeproject.repository.IngredientScanRepository;
import com.hajdugellert.recipeproject.vision.GoogleVisionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class IngredientScanService {
    @Autowired
    private IngredientScanRepository ingredientScanRepository;

    @Autowired
    private GoogleVisionClient googleVisionClient;

    private static final Set<String> KNOWN_INGREDIENTS = Set.of(
            "chicken",
            "beef",
            "pork",
            "fish",
            "egg",
            "milk",
            "cheese",
            "pasta",
            "rice",
            "tomato",
            "onion",
            "garlic",
            "potato",
            "carrot",
            "pepper",
            "lettuce",
            "bread",
            "banana",
            "apple",
            "mushroom",
            "cucumber",
            "broccoli",
            "corn",
            "lemon",
            "lime",
            "orange",
            "strawberry"
    );

    public IngredientScanResponse scanImage(MultipartFile image)
    {
        validateImage(image);
        List<String> labels = new ArrayList<>(googleVisionClient.detectLabels(image));
        List<String> ingredients = labels.stream().filter(label -> KNOWN_INGREDIENTS.contains(label)).toList();
        IngredientScan scan = new IngredientScan();

        scan.setDetectedIngredients(ingredients);
        scan.setDetectedLabels(labels);
        scan.setFileName(image.getOriginalFilename());
        scan.setFileFormat(image.getContentType());
        scan.setFilesSize(image.getSize());
        IngredientScan savedScan = ingredientScanRepository.save(scan);
        return IngredientScanMapper.toResponse(savedScan);


    }
    private void validateImage(MultipartFile image) {
        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("Image file is required");
        }

        if (image.getContentType() == null || !image.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("File must be an image");
        }

        long maxSize = 5 * 1024 * 1024;

        if (image.getSize() > maxSize) {
            throw new IllegalArgumentException("Image must be smaller than 5MB");
        }
    }

}

