package com.example.recipesapp.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Первый запуск")
public class FirstControllers {
    @GetMapping
    public String app() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String getInfo() {
        return "Имя ученика: Чемпогир Владимир " +
                " Название проекта: Кулинария  " +
                " Дата создания: 14.03.2023" +
                " Описание проекта: Пошаговая готовка еды ";

    }
}
