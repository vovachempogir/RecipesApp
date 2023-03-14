package com.example.recipesapp.controllers;

import com.example.recipesapp.model.Ingredient;
import com.example.recipesapp.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        Ingredient createIngredient = ingredientService.addIngredient(ingredient);
        return createIngredient;
    }

    @GetMapping("/{ingredientNumber}")
    public Ingredient getIngredient(@PathVariable int ingredientNumber) {
        Ingredient ingredient = ingredientService.getIngredient(ingredientNumber);
        return ingredient;
    }
}

