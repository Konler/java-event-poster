package ru.practicum.mainservice.controllers.admins;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.CategoryDto;
import ru.practicum.mainservice.dto.NewCategoryDto;
import ru.practicum.mainservice.services.admins.CategoriesAdminService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class CategoriesAdminController {
    private final CategoriesAdminService categoriesAdminService;

    @PostMapping
    CategoryDto addCategory(@RequestBody @Valid NewCategoryDto newCategoryDto) {
        log.info("Добавление новой категории: {}", newCategoryDto);
        return categoriesAdminService.addCategory(newCategoryDto);
    }

    @DeleteMapping("/catId")
    void deleteCategoryById(@PathVariable Integer id) {
        log.info("Удаление категории");
        categoriesAdminService.deleteCategoryById(id);

    }

    @PatchMapping("/catId")
    CategoryDto updateCategory(@PathVariable Integer catId, @RequestBody @Valid CategoryDto categoryDto) {
        log.info("Изменение категории по id: {}", catId);
        return categoriesAdminService.updateCategory(catId, categoryDto);
    }
}



