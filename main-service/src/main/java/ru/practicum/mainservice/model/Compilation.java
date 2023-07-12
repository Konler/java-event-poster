package ru.practicum.mainservice.model;

import lombok.*;

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
