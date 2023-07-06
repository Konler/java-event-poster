package ru.practicum.mainservice.model;

import java.util.List;

public class CompilationDto {
    Integer id;
    List<EventShortDto> events;
    Boolean pinned;
    String title;
}
