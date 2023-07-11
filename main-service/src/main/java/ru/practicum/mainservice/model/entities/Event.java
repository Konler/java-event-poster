package ru.practicum.mainservice.model.entities;

import lombok.Builder;
import lombok.Data;
import ru.practicum.mainservice.StateOfEvent;
import ru.practicum.mainservice.dto.CategoryDto;
import ru.practicum.mainservice.dto.UserShortDto;
import ru.practicum.mainservice.model.Location;

import java.time.LocalDateTime;

@Builder
@Data
public class Event {
    Integer id;
    String annotation;
    CategoryDto category;
    Integer confirmedRequests;
    LocalDateTime createdOn;
    String description;

    LocalDateTime eventDate;
    UserShortDto initiator;
    Location location;
    Boolean paid;

    Integer participantLimit;

    LocalDateTime publishedOn;

    Boolean requestModeration;
    StateOfEvent state;
    String title;
    Integer views;
}
