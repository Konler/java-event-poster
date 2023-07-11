package ru.practicum.mainservice.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;
@Builder
@Data
public class NewCategoryDto {
    @Size(min = 1, max = 50, message = "Название категории должно быть размером от 1 до 50")
    String name;
}
