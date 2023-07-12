package ru.practicum.mainservice.mappers;

import lombok.experimental.UtilityClass;
import ru.practicum.mainservice.dto.category.CategoryDto;
import ru.practicum.mainservice.dto.category.NewCategoryDto;
import ru.practicum.mainservice.model.Category;

@UtilityClass
public class CategoryMapper {

    public Category toCategory(NewCategoryDto newCategoryDto) {
        return Category.builder().name(newCategoryDto.getName()).build();
    }

    public CategoryDto toCategoryDto(Category category) {
        return CategoryDto.builder().id(category.getId()).name(category.getName()).build();
    }

    public Category fromCategoryDtoToCategory(CategoryDto categoryDto) {
        return Category.builder().id(categoryDto.getId()).name(categoryDto.getName()).build();
    }
}
