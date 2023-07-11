package ru.practicum.mainservice.controllers.publics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.CategoryDto;
import ru.practicum.mainservice.services.publics.CategoriesPublicService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoriesPublicController {
    private final CategoriesPublicService categoriesPublicService;

    @GetMapping
    CategoryDto getCategories(@RequestParam(name = "from", defaultValue = "0", required = false) Integer from,
                              @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        log.info("Получение категории");
        return categoriesPublicService.getCategories(from, size);
    }

    @GetMapping("/{catId}")
    CategoryDto getCategoryById(@PathVariable Integer catId) {

        log.info("Получение инфрмации о категории по ее id: {}", catId);
        return categoriesPublicService.getCategoryById(catId);
    }
}
