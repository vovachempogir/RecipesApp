package com.example.recipesapp.services.impl;

import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.services.RecipeService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        ObjectUtils.isNotEmpty(recipes);
        return recipes.get(recipeNumber);
    }

    @Override
    public List<Recipe> getAllRecipe() {
        ObjectUtils.isNotEmpty(recipes);
        List allRecipe = new ArrayList<>();
        for (Map.Entry<Long, Recipe> recipe : recipes.entrySet()) {
            allRecipe.add(recipe);
        }
        return allRecipe;
    }

    @Override
    public Recipe editRecipe(long recipeNumber, Recipe recipe) {
        ObjectUtils.isNotEmpty(recipes);
        if (recipes.containsKey(recipeNumber)) {
            recipes.put(recipeNumber, recipe);
            return recipe;
        }
        return recipe;
    }

    @Override
    public boolean deleteIngredient(long recipeNumber) {
        ObjectUtils.isNotEmpty(recipes);
        if (recipes.containsKey(recipeNumber)) {
            recipes.remove(recipeNumber);
            return true;
        }
        return false;
    }
}
