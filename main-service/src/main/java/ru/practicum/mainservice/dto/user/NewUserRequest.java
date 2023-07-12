package ru.practicum.mainservice.dto.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewUserRequest {
    @Email
    @Size(min = 6, max = 254, message = "Длина почты должна быть в диапазоне от 6 до 254")
    String email;
    String name;
}
