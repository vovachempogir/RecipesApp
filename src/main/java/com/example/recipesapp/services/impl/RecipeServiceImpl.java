package com.example.recipesapp.services.impl;

import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static final Map<Long, Recipe> recipes = new HashMap<>();
    private static long recipeNumber = 0;

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipes.put(recipeNumber++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(long recipeNumber) {
        return recipes.get(recipeNumber);
    }

    @Override
    public Recipe editRecipe(long recipeNumber, Recipe recipe) {
        if (recipes.containsKey(recipeNumber)) {
            recipes.put(recipeNumber, recipe);
            return recipe;
        }
        return recipe;
    }

    @Override
    public boolean deleteIngredient(long recipeNumber) {
        if (recipes.containsKey(recipeNumber)) {
            recipes.remove(recipeNumber);
            return true;
        }
        return false;
    }
}
