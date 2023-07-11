package ru.practicum.mainservice.services.publics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.mainservice.dto.CategoryDto;
import ru.practicum.mainservice.repositories.CategoryRepository;
@Slf4j
@RequiredArgsConstructor
@Service
public class CategoriesPublicServiceImpl implements CategoriesPublicService{

    private final CategoryRepository categoryRepository;
    @Override
    public CategoryDto getCategories(Integer from, Integer size) {
        return null;
    }

    @Override
    public CategoryDto getCategoryById(Integer catId) {
        return null;
    }
}
