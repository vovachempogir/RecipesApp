package com.example.recipesapp.services;

import com.example.recipesapp.model.Recipe;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(long recipeNumber);
}
