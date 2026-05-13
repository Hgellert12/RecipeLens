package com.hajdugellert.recipeproject.service;
import com.hajdugellert.recipeproject.dto.CreateRecipeRequest;
import com.hajdugellert.recipeproject.dto.RecipeResponse;
import com.hajdugellert.recipeproject.dto.UpdateRecipeRequest;
import com.hajdugellert.recipeproject.mapper.RecipeMapper;
import com.hajdugellert.recipeproject.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hajdugellert.recipeproject.entity.*;

import java.util.List;


@Service
public class RecipeService {
@Autowired
private RecipeRepository recipeRepository;

public RecipeResponse createRecipe(CreateRecipeRequest request)
{
    Recipe recipe = RecipeMapper.toEntity(request);
    Recipe savedRecipe = recipeRepository.save(recipe);
    return RecipeMapper.toResponse(savedRecipe);
}
public List<RecipeResponse> getAllRecipes()
{
    List<Recipe> recipeList = recipeRepository.findAll();
    return recipeList.stream().map(RecipeMapper::toResponse).toList();
}
public RecipeResponse getRecipeById(Long id)
{
    Recipe recipe = findRecipeEntityById(id);
    return RecipeMapper.toResponse(recipe);
}
public List<RecipeResponse> getByName(String name)
{
    List<Recipe> recipeList = recipeRepository.findByNameContainingIgnoreCase(name);
    return recipeList.stream().map(RecipeMapper::toResponse).toList();
}
public List<RecipeResponse> getByCategory(String category)
{
    List<Recipe> recipeList = recipeRepository.findByCategoryIgnoreCase(category);
    return  recipeList.stream().map(RecipeMapper::toResponse).toList();
}
public List<RecipeResponse> getByFavorite(Boolean favorite)
{
    List<Recipe> recipeList = recipeRepository.findByFavorite(favorite);
    return recipeList.stream().map(RecipeMapper::toResponse).toList();
}
public RecipeResponse updateRecipe(Long id, UpdateRecipeRequest newRecipe)
{
    Recipe recipe = findRecipeEntityById(id);
    if(newRecipe.name() != null)
    {
        recipe.setName(newRecipe.name());
    }
    if(newRecipe.cost() != null)
    {
        recipe.setCost(newRecipe.cost());
    }
    if(newRecipe.description() != null)
    {
        recipe.setDescription(newRecipe.description());
    }
    if(newRecipe.ingredients() != null)
    {
        recipe.setIngredients(newRecipe.ingredients());
    }
    if(newRecipe.instructions() != null)
    {
        recipe.setInstructions(newRecipe.instructions());
    }
    if(newRecipe.prepTime() != null)
    {
        recipe.setPrepTime(newRecipe.prepTime());
    }
    if(newRecipe.category() != null)
    {
        recipe.setCategory(newRecipe.category());
    }

    Recipe savedRecipe = recipeRepository.save(recipe);
    return RecipeMapper.toResponse(savedRecipe);
}
public void deleteRecipe(Long id)
{
    Recipe recipe = findRecipeEntityById(id);
    recipeRepository.delete(recipe);


}
public List<RecipeResponse> getByUser(String writtenBy)
    {
        List<Recipe> recipeList = recipeRepository.findByWrittenByUsernameIgnoreCase(writtenBy);
        return recipeList.stream().map(RecipeMapper::toResponse).toList();
    }
    private Recipe findRecipeEntityById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with id: " + id));
    }

}
