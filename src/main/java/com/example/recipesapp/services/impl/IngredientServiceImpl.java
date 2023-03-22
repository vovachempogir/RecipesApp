package com.example.recipesapp.services.impl;

import com.example.recipesapp.model.Ingredient;
import com.example.recipesapp.services.IngredientService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        ObjectUtils.isNotEmpty(ingredients);
        return ingredients.get(ingredientNumber);
    }

    @Override
    public List<Ingredient> getAllIngredient() {
        ObjectUtils.isNotEmpty(ingredients);
        List allIngredient = new ArrayList<>();
        for (Map.Entry<Long, Ingredient> ingredient : ingredients.entrySet()) {
            allIngredient.add(ingredient);
        }
        return allIngredient;
    }

    @Override
    public Ingredient editIngredient(long ingredientNumber, Ingredient ingredient) {
        ObjectUtils.isNotEmpty(ingredients);
        if (ingredients.containsKey(ingredientNumber)) {
            ingredients.put(ingredientNumber, ingredient);
            return ingredient;
        }
        return ingredient;
    }

    @Override
    public boolean deleteIngredient(long ingredientNumber) {
        ObjectUtils.isNotEmpty(ingredients);
        if (ingredients.containsKey(ingredientNumber)) {
            ingredients.remove(ingredientNumber);
            return true;
        }
        return false;
    }
}
