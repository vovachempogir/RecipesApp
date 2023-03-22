package com.example.recipesapp.services.impl;

import com.example.recipesapp.services.RecipeFilesService;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RecipeFilesServiceImpl implements RecipeFilesService {
    @Value("${path.to.recipes.file}")
    private String recipeFilePath;

    @Value("${name.of.recipes.file}")
    private String recipeFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(recipeFilePath, recipeFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(recipeFilePath, recipeFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void cleanDataFile() {
        try {
            Path path = Path.of(recipeFilePath, recipeFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
