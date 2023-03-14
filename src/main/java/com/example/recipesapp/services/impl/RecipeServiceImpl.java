package com.example.recipesapp.services.impl;

import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static Map<Long, Recipe> recipes = new HashMap<>();
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

}
