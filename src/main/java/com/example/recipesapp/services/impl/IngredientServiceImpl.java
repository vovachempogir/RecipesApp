package com.example.recipesapp.services.impl;

import com.example.recipesapp.model.Ingredient;
import com.example.recipesapp.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static final Map<Long, Ingredient> ingredients = new HashMap<>();
    private static long ingredientNumber = 0;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredients.put(ingredientNumber++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(long ingredientNumber) {
        return ingredients.get(ingredientNumber);
    }

    @Override
    public Ingredient editIngredient(long ingredientNumber, Ingredient ingredient) {
        if (ingredients.containsKey(ingredientNumber)) {
            ingredients.put(ingredientNumber, ingredient);
            return ingredient;
        }
        return ingredient;
    }

    @Override
    public boolean deleteIngredient(long ingredientNumber) {
        if (ingredients.containsKey(ingredientNumber)) {
            ingredients.remove(ingredientNumber);
            return true;
        }
        return false;
    }
}
