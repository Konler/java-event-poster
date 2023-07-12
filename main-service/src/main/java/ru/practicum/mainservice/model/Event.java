package ru.practicum.mainservice.model;

import lombok.Builder;
import lombok.Data;
import ru.practicum.mainservice.dto.category.CategoryDto;
import ru.practicum.mainservice.dto.user.UserShortDto;
import ru.practicum.mainservice.enums.StateOfEvent;

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
