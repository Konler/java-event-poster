package ru.practicum.mainservice.dto.category;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Size;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewCategoryDto {
    @Size(min = 1, max = 50, message = "Название категории должно быть размером от 1 до 50")
    String name;
}
