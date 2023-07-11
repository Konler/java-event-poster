package ru.practicum.mainservice.model.entities;

import lombok.*;
import ru.practicum.mainservice.dto.EventShortDto;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Compilation {
    Integer id;
    List<Event> events;
    Boolean pinned;
    String title;
}
