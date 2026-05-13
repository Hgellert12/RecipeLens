package com.hajdugellert.recipeproject.controller;
import com.hajdugellert.recipeproject.dto.CreateRecipeRequest;
import com.hajdugellert.recipeproject.dto.RecipeResponse;
import com.hajdugellert.recipeproject.dto.UpdateRecipeRequest;
import jakarta.validation.Valid;
import com.hajdugellert.recipeproject.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @PostMapping
    public ResponseEntity<RecipeResponse> createRecipe(@Valid @RequestBody CreateRecipeRequest newRecipe)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.createRecipe(newRecipe));
    }
    @GetMapping
    public ResponseEntity<List<RecipeResponse>> getAllRecipes()
    {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable Long id)
    {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<RecipeResponse> updateRecipe(@PathVariable Long id, @Valid @RequestBody UpdateRecipeRequest recipe)
    {

        return ResponseEntity.ok(recipeService.updateRecipe(id, recipe));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id)
    {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<RecipeResponse>> getRecipeByName(@RequestParam String name)
    {

        return ResponseEntity.ok(recipeService.getByName(name));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<RecipeResponse>> getRecipeByCategory(@PathVariable String category)
    {
        return ResponseEntity.ok(recipeService.getByCategory(category));
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<RecipeResponse>> getFavoriteRecipes()
    {
        return ResponseEntity.ok(recipeService.getByFavorite(true));
    }
    @GetMapping("/user/{username}")
    public ResponseEntity<List<RecipeResponse>> getRecipesByUser(@PathVariable String username)
    {
        return ResponseEntity.ok(recipeService.getByUser(username));
    }



}
