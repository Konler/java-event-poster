package ru.practicum.mainservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Stats {
    private String app;
    private String uri;
    private Integer hits;
}

