package ru.practicum.mainservice.dto.compilation;

import lombok.Builder;
import lombok.Data;
import ru.practicum.mainservice.dto.event.EventShortDto;

import javax.validation.constraints.NotNull;
import java.util.List;
@Builder
@Data
public class CompilationDto {
    Integer id;
    List<EventShortDto> events;

    Boolean pinned;

    String title;
}
