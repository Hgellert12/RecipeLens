package com.hajdugellert.recipeproject.dto;
import jakarta.validation.constraints.*;

import java.util.List;

public record CreateRecipeRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must be less than 100 characters")
        String name,

        @NotBlank(message = "Description is required")
        @Size(max = 500, message = "Description must be less than 500 characters")
        String description,

        @NotBlank(message = "Category is required")
        @Size(max = 50, message = "Category must be less than 50 characters")
        String category,

        @NotEmpty(message = "At least one ingredient is required")
        List<@NotBlank(message = "Ingredients cannot be blank") String> ingredients,

        @NotBlank(message = "Instructions are required")
        @Size(min = 10, message = "Instructions must contain at least 10 characters")
        String instructions,

        @NotNull(message = "Preparation time is required")
        @Positive(message = "Preparation time must be a positive number")
        Integer prepTime,

        @Positive(message = "Cost must be a positive number")
        @NotNull(message = "Cost is required")
        Double cost




        )

{}

