package com.example.recipesapp.services;


import java.io.File;

public interface IngredientFilesService {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();
}
