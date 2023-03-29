package com.example.recipesapp.services;

import com.example.recipesapp.model.Ingredient;

import java.util.List;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getIngredient(long ingredientNumber);

    List<Ingredient> getAllIngredient();

    Ingredient editIngredient(long ingredientNumber, Ingredient ingredient);

    boolean deleteIngredient(long ingredientNumber);
}