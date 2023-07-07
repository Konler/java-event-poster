package ru.practicum.mainservice.controllers.admins;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.model.CategoryDto;
import ru.practicum.mainservice.model.NewCategoryDto;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class CategoriesController {

    @PostMapping
    CategoryDto addCategory(@RequestBody @Valid NewCategoryDto newCategoryDto) {
        log.info("Добавление новой категории: {}",newCategoryDto);
        return null;
    }

    @DeleteMapping("/catId")
    void deleteCategoryById(@PathVariable Integer id) {
        log.info("Удаление категории");

    }

    @PatchMapping("/catId")
    void updateCategory(@PathVariable Integer catId, @RequestBody @Valid CategoryDto categoryDto) {
        log.info("Изменение категории по id: {}",catId);

    }
}



