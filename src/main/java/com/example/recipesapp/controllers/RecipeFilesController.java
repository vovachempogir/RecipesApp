package com.example.recipesapp.controllers;

import com.example.recipesapp.model.Recipe;
import com.example.recipesapp.services.RecipeFilesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/recipe/files")
@Tag(name = "Контроллер для файлов с рецептами", description = "Операции с файлами рецептов")
public class RecipeFilesController {
    private final RecipeFilesService recipeFilesService;

    public RecipeFilesController(RecipeFilesService recipeFilesService) {
        this.recipeFilesService = recipeFilesService;
    }

    @GetMapping("/export")
    @Operation(
            summary = "экспорт файлов с рецептами",
            description = "экспорт"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты успешно загружены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Recipe.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "В файле рецептов нет содержимого",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
        File file = recipeFilesService.getDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipesLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Загрузка нового файла с рецептами",
            description = "Загрузка нового файла рецептов"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Новый файл с рецептами успешно загружен"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Внутренняя ошибка сервера"
            )
    })
    public ResponseEntity<Void> uploadDataFIle(@RequestParam MultipartFile file) {
        recipeFilesService.uploadDataFile(file);
        if (file.getResource().exists()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
