package ru.practicum.mainservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserShortDto {
    Integer id;
    String name;
}
