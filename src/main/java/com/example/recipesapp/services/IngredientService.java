package com.example.recipesapp.services;

import com.example.recipesapp.model.Ingredient;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getIngredient(long ingredientNumber);
}
