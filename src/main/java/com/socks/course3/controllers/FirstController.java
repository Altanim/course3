package com.socks.course3.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Информация", description = "Информация")
public class FirstController {
    @GetMapping("/info")
    public String info(){
        return "Курсовая работа №3\n" +
                "Ученик Назаренко Андрей\n" +
                "22.01.2023";
    }

}

