package ru.practicum.mainservice.services.admins;

import ru.practicum.mainservice.dto.CategoryDto;
import ru.practicum.mainservice.dto.NewCategoryDto;

public interface CategoriesAdminService {
    CategoryDto addCategory(NewCategoryDto newCategoryDto);

    void deleteCategoryById(Integer id);

    CategoryDto updateCategory(Integer catId, CategoryDto categoryDto);
}
