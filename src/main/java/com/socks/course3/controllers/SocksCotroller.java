package com.socks.course3.controllers;

import com.socks.course3.model.*;
import com.socks.course3.service.SockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/socks")
@Tag(name = "Носки", description = "Работа со складом")
public class SocksCotroller {
    private final SockService sockService;

    public SocksCotroller(SockService sockService) {
        this.sockService = sockService;
    }

    @PostMapping("/add")
    @Operation(summary = "Добавить носки на склад", description = "Добавить на склад")
    @ApiResponse(responseCode = "200", description = "Удалось добавить приход")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<?> add(@RequestBody SockItem sockItem) {
        sockService.add(sockItem);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    @Operation(summary = "Проверить наличие по параметрам", description = "Наличие на складе по параметрам")
    @ApiResponse(responseCode = "200", description = "запрос выполнен, результат в теле ответа в виде " +
            "строкового представления целого числа")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Integer> getSocks(@RequestParam String color,
                                            @RequestParam int size,
                                            @RequestParam(required = false, defaultValue = "0") int cottonMin,
                                            @RequestParam(required = false, defaultValue = "100") int cottonMax) {
        int available = sockService.get(color, size, cottonMin, cottonMax);
        return ResponseEntity.ok(available);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Списать", description = "Списание со склада")
    @ApiResponse(responseCode = "200", description = "запрос выполнен, товар списан со склада")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")

    public ResponseEntity<?> delete(@RequestBody SockItem sockItem) {
        sockService.put(sockItem);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/put")
    @Operation(summary = "Выдать носки со склада", description = "Выдать со склада")
    @ApiResponse(responseCode = "200", description = "Удалось произвести отпуск носков со склада")
    @ApiResponse(responseCode = "400", description = "Товара нет на складе в нужном количестве или " +
            "параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")

    public ResponseEntity<?> take(@RequestBody SockItem sockItem) {
        sockService.put(sockItem);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Проверить весь список наличия", description = "Получение списка носков")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Получение успешно"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    })
    @GetMapping
    public Map<Socks, Integer> getAll() {
        return sockService.getAll();
    }
}
