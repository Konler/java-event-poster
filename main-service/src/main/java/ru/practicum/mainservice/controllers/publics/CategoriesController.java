package ru.practicum.mainservice.controllers.publics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.model.CategoryDto;


@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoriesController {

    @GetMapping
    CategoryDto getCategories(@RequestParam(name = "from") Integer from,
                              @RequestParam(name = "size") Integer size) {
        return null;
    }

    @GetMapping("/{catId}")
    CategoryDto getCategoryById(@PathVariable Integer catId) {
        return null;
    }
}
