package ru.practicum.mainservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.mainservice.dto.category.CategoryDto;
import ru.practicum.mainservice.dto.category.NewCategoryDto;
import ru.practicum.mainservice.exceptions.ConflictModerationException;
import ru.practicum.mainservice.exceptions.NotFoundException;
import ru.practicum.mainservice.mappers.CategoryMapper;
import ru.practicum.mainservice.model.Category;
import ru.practicum.mainservice.repositories.CategoryRepository;
import ru.practicum.mainservice.util.General;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto addCategory(NewCategoryDto newCategoryDto) {
        Category category = CategoryMapper.toCategory(newCategoryDto);
        return CategoryMapper.toCategoryDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryRepository.delete(categoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Категория с данным Id" + id + " не найденна")));
    }

    @Override
    public CategoryDto updateCategory(Integer catId, CategoryDto categoryDto) {

        Category category=categoryRepository.findById(catId).orElseThrow(() -> new NotFoundException("Категория не найденна"));
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return CategoryMapper.toCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getCategories(Integer from, Integer size) {
        return categoryRepository.findAll(General.toPage(from,size))
                .stream()
                .map(CategoryMapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Integer catId) {
        return CategoryMapper.toCategoryDto(categoryRepository.findById(catId).orElseThrow(() -> new NotFoundException("Категория не найденна")));
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Категория не найдеаннас таким id"+id));
    }

}
