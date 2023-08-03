package ru.practicum.mainservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Stats {
    String app;
    String uri;
    Integer hits;
}

