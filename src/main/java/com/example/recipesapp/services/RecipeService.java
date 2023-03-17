package com.example.recipesapp.services;

import com.example.recipesapp.model.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(long recipeNumber);

    List<Recipe> getAllRecipe();

    Recipe editRecipe(long recipeNumber, Recipe recipe);

    boolean deleteIngredient(long recipeNumber);
}