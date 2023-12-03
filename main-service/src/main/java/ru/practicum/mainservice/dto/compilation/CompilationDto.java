package ru.practicum.mainservice.dto.compilation;

import lombok.Builder;
import lombok.Data;
import ru.practicum.mainservice.dto.event.EventShortDto;

import java.util.List;

@Builder
@Data
public class CompilationDto {
    private Integer id;
    private List<EventShortDto> events;

    private Boolean pinned;

    private String title;
}
