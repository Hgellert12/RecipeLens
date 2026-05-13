package com.hajdugellert.recipeproject.mapper;
import com.hajdugellert.recipeproject.dto.CreateRecipeRequest;
import com.hajdugellert.recipeproject.dto.RecipeResponse;
import com.hajdugellert.recipeproject.entity.Recipe;
public class RecipeMapper {
public static Recipe toEntity(CreateRecipeRequest request)
{
    Recipe recipe = new Recipe();
    recipe.setName(request.name());
    recipe.setDescription(request.description());
    recipe.setCategory(request.category());
    recipe.setIngredients(request.ingredients());
    recipe.setInstructions(request.instructions());
    recipe.setPrepTime(request.prepTime());
    recipe.setCost(request.cost());
    recipe.setFavorite(false);
    return recipe;
}
public static RecipeResponse toResponse(Recipe recipe)
{
    String writtenByUserName = recipe.getWrittenBy()==null ? null: recipe.getWrittenBy().getUsername();
    return new RecipeResponse(
            recipe.getId(),
            recipe.getName(),
            recipe.getDescription(),
            recipe.getCategory(),
            recipe.getIngredients(),
            recipe.getInstructions(),
            recipe.getPrepTime(),
            recipe.getCost(),
            recipe.getFavorite(),
            writtenByUserName,
            recipe.getCreatedAt()
    );

}


}
