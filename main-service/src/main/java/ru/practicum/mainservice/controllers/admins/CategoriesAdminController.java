package ru.practicum.mainservice.controllers.admins;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.category.CategoryDto;
import ru.practicum.mainservice.dto.category.NewCategoryDto;
import ru.practicum.mainservice.services.CategoriesService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class CategoriesAdminController {

    private final CategoriesService categoriesService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@RequestBody @Valid NewCategoryDto newCategoryDto) {
        log.info("Received a request to create a category");
        CategoryDto categoryDto = categoriesService.addCategory(newCategoryDto);
        log.info("Category id={} successfully created", categoryDto.getId());
        return categoryDto;
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCategoryById(@PathVariable Integer catId) {
        log.info("Удаление категории");
        categoriesService.deleteCategoryById(catId);

    }

    @PatchMapping("/{catId}")
    CategoryDto updateCategory(@PathVariable Integer catId, @RequestBody @Valid CategoryDto categoryDto) {
        log.info("Изменение категории по id: {}", catId);
        return categoriesService.updateCategory(catId, categoryDto);
    }
}



