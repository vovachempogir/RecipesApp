package com.example.recipesapp.services.impl;

import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.services.RecipeFilesService;
import com.example.recipesapp.services.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeFilesService recipeFilesService;
    private static Map<Long, Recipe> recipes = new HashMap<>();
    private static long recipeNumber = 0;

    public RecipeServiceImpl(RecipeFilesService recipeFilesService) {
        this.recipeFilesService = recipeFilesService;
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
    public Recipe addRecipe(Recipe recipe) {
        recipes.put(recipeNumber++, recipe);
        saveToFile();
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
            saveToFile();
            readFromFile();
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(long recipeNumber) {
        ObjectUtils.isNotEmpty(recipes);
        if (recipes.containsKey(recipeNumber)) {
            recipes.remove(recipeNumber);
            saveToFile();
            return true;
        }
        return false;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            recipeFilesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = recipeFilesService.readFromFile();
        try {
            recipes = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Path createTextDataFile() throws IOException {
        Path path = recipeFilesService.createTempFile("recipesDataFile");
        for (Recipe recipe : recipes.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append(recipe.getName()).append("\n \n").append("Время приготовления: ").append(String.valueOf(recipe.getCookingTime())).append(" минуты.").append("\n");
                writer.append("\n");
                writer.append("Ингредиенты: \n \n");
                recipe.getIngredients().forEach(ingredient -> {
                    try {
                        writer.append(" - ").append(ingredient.getName()).append(" - ").append(String.valueOf(ingredient.getNumberOfIngredients())).append(" ").append(ingredient.getUnitOfMeasurement()).append("\n \n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                writer.append("\n");
                writer.append("Инструкция приготовления: \n \n");
                recipe.getCookingSteps().forEach(step -> {
                    try {
                        writer.append(" > ").append(step).append("\n \n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                writer.append("\n \n");
            }
        }
        return path;
    }
}
