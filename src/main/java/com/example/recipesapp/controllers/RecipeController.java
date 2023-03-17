package com.example.recipesapp.controllers;

import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        Recipe createRecipe = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(createRecipe);
    }

    @GetMapping("/{recipeNumber}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable long recipeNumber){
        Recipe recipe = recipeService.getRecipe(recipeNumber);
        if (recipe == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @GetMapping
    public List<Recipe> getAllRecipe() {
        return recipeService.getAllRecipe();
    }

    @PutMapping("/{recipeNumber}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable long recipeNumber, @RequestBody Recipe recipe) {
        Recipe editRecipe = recipeService.editRecipe(recipeNumber, recipe);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editRecipe);
    }

    @DeleteMapping("/{recipeNumber}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable long recipeNumber) {
        if (recipeService.deleteIngredient(recipeNumber)) {
            ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
