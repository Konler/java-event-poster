package ru.practicum.mainservice.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
@Builder
@Data
public class NewUserRequest {
    @Email
    @Size(min = 6, max = 254, message = "Длина почты должна быть в диапазоне от 6 до 254")
    String email;
    String name;

}
