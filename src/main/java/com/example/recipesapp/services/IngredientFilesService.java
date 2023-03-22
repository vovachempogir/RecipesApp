package com.example.recipesapp.services;

public interface IngredientFilesService {
    boolean saveToFile(String json);

    String readFromFile();
}
