package ru.practicum.mainservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class CompilationDto {
    Integer id;
    List<EventShortDto> events;
    Boolean pinned;
    String title;
}
