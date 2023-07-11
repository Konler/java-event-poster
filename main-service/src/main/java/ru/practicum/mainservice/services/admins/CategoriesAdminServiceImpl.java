package ru.practicum.mainservice.services.admins;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.mainservice.dto.CategoryDto;
import ru.practicum.mainservice.dto.NewCategoryDto;
import ru.practicum.mainservice.repositories.CategoryRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoriesAdminServiceImpl implements CategoriesAdminService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto addCategory(NewCategoryDto newCategoryDto) {
        //categoryRepository.save(newCategoryDto)
        return null;
    }

    @Override
    public void deleteCategoryById(Integer id) {

    }

    @Override
    public CategoryDto updateCategory(Integer catId, CategoryDto categoryDto) {
        return null;
    }
}
