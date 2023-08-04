package ru.practicum.mainservice.dto.user;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;

@Builder
@Data
public class UserDto {
    Integer id;
    @Email
    String email;
    String name;
}
