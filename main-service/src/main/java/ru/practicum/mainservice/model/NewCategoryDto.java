package ru.practicum.mainservice.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class NewCategoryDto {
    @Size(min =1,max = 50,message ="Название категории должно быть размером от 1 до 50")
    String name;
}
