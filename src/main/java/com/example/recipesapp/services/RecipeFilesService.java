package com.example.recipesapp.services;


import java.io.File;
import java.nio.file.Path;

public interface RecipeFilesService {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    Path createTempFile(String suffix);

    boolean cleanDataFile();
}
