package com.hajdugellert.recipeproject.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UpdateRecipeRequest
        (
                @Size(max = 100,message ="The name must be less than 100 characters")
                String name,
                @Size(max = 500,message="The description must be less than 500 characters")
                String description,
                @Size(max = 50, message = "The category must be less than 50 characters")
                String category,
                @Size(min = 1, message="At least one ingredient is required")
                List<@NotBlank(message = "Ingredients cannot be blank") String> ingredients,
                @Size(min = 10, message ="Instructions must contain at least 10 characters")
                String instructions,
                @Positive(message = "Preparation time must be a positive number")
                Integer prepTime,
                @Positive(message = "Cost must be a positive number")
                Double cost

        )
{
}
