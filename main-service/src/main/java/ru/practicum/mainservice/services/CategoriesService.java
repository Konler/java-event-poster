package ru.practicum.mainservice.services;

import ru.practicum.mainservice.dto.category.CategoryDto;
import ru.practicum.mainservice.dto.category.NewCategoryDto;

import java.util.List;

public interface CategoriesService {
    CategoryDto addCategory(NewCategoryDto newCategoryDto);

    void deleteCategoryById(Integer id);

    CategoryDto updateCategory(Integer catId, CategoryDto categoryDto);

    List<CategoryDto> getCategories(Integer from, Integer size);
    CategoryDto getCategoryById( Integer catId);
}
