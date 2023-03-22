package com.example.recipesapp.services;

public interface RecipeFilesService {
    boolean saveToFile(String json);

    String readFromFile();
}
