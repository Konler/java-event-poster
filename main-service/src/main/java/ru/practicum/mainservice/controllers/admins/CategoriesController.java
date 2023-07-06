package ru.practicum.mainservice.controllers.admins;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.model.CategoryDto;
import ru.practicum.mainservice.model.NewCategoryDto;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class CategoriesController {

    @PostMapping
    CategoryDto addCategory(@RequestBody NewCategoryDto newCategoryDto) {

    return null;
    }

    @DeleteMapping("/catId")
    void deleteCategoryById(@PathVariable Integer id) {

    }

    @PatchMapping("/catId")
    void updateCategory(@PathVariable Integer carId, @RequestBody CategoryDto categoryDto) {

    }
}



