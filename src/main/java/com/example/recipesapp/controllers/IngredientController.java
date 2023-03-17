package com.example.recipesapp.controllers;

import com.example.recipesapp.model.Ingredient;
import com.example.recipesapp.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        Ingredient createIngredient = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(createIngredient);
    }

    @GetMapping("/{ingredientNumber}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable long ingredientNumber) {
        Ingredient ingredient = ingredientService.getIngredient(ingredientNumber);
        if (ingredient == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping("/allIngredient")
    public List<Ingredient> getAll() {
        return ingredientService.getAllIngredient();
    }

    @PutMapping("/{ingredientNumber}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable long ingredientNumber, @RequestBody Ingredient ingredient) {
        Ingredient editIngredient = ingredientService.editIngredient(ingredientNumber, ingredient);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editIngredient);
    }

    @DeleteMapping("/{ingredientNumber}")
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable long ingredientNumber) {
        if (ingredientService.deleteIngredient(ingredientNumber)) {
            ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
