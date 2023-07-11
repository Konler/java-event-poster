package ru.practicum.mainservice.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
@Builder
@Data
public class UserDto {
    @Email
    String email;
    Integer id;
    String name;
}
