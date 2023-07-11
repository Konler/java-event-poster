package ru.practicum.mainservice.services.publics;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.mainservice.dto.CategoryDto;

public interface CategoriesPublicService {
    CategoryDto getCategories( Integer from, Integer size);
    CategoryDto getCategoryById(@PathVariable Integer catId);
}
