package com.example.recipesapp.controllers;

import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @GetMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        Recipe createRecipe = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(createRecipe);
    }
    @GetMapping("/{recipeNumber}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable int recipeNumber){
        Recipe recipe = recipeService.getRecipe(recipeNumber);
        if (recipe == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
}
