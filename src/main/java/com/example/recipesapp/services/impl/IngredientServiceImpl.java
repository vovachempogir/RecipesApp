package com.example.recipesapp.services.impl;

import com.example.recipesapp.model.Ingredient;
import com.example.recipesapp.services.IngredientFilesService;
import com.example.recipesapp.services.IngredientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientFilesService ingredientFilesService;
    private static Map<Long, Ingredient> ingredients = new HashMap<>();
    private static long ingredientNumber = 0;

    public IngredientServiceImpl(IngredientFilesService ingredientFilesService) {
        this.ingredientFilesService = ingredientFilesService;
    }


    @PostConstruct
    private void init() {
        try {
            readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredients.put(ingredientNumber++, ingredient);
        saveToFile();
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
            saveToFile();
            readFromFile();
            return ingredient;
        }
        return  null;
    }

    @Override
    public boolean deleteIngredient(long ingredientNumber) {
        ObjectUtils.isNotEmpty(ingredients);
        if (ingredients.containsKey(ingredientNumber)) {
            ingredients.remove(ingredientNumber);
            saveToFile();
            return true;
        }
        return false;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            ingredientFilesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = ingredientFilesService.readFromFile();
        try {
            ingredients = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
