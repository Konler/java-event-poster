package ru.practicum.mainservice.controllers.publics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.category.CategoryDto;
import ru.practicum.mainservice.services.CategoriesService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoriesPublicController {
    private final CategoriesService categoriesService;

    @GetMapping
        //есть
    List<CategoryDto> getCategories(@RequestParam(name = "from", defaultValue = "0") Integer from,
                                    @RequestParam(name = "size", defaultValue = "10") Integer size) {
        log.info("Получение категорий");
        return categoriesService.getCategories(from, size);
    }

    @GetMapping("/{catId}")
    CategoryDto getCategoryById(@PathVariable Integer catId) {

        log.info("Получение инфрмации о категории по ее id: {}", catId);
        return categoriesService.getCategoryById(catId);
    }
}
