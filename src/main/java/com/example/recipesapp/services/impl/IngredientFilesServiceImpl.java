package com.example.recipesapp.services.impl;

import com.example.recipesapp.services.IngredientFilesService;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IngredientFilesServiceImpl implements IngredientFilesService {
    @Value("${path.to.ingredients.file}")
    private String ingredientFilePath;

    @Value("${name.of.ingredients.file}")
    private String ingredientFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(ingredientFilePath, ingredientFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(ingredientFilePath, ingredientFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void cleanDataFile() {
        try {
            Path path = Path.of(ingredientFilePath, ingredientFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}