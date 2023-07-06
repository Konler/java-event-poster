package ru.practicum.mainservice.model;


import ru.practicum.mainservice.State;

public class EventFullDto {
    Integer id;
    String annotation;
    CategoryDto category;
    Integer confirmedRequests;
    String createdOn;
    String description;
    String eventDate;
    UserShortDto initiator;
    Location location;
    Boolean paid;
    Integer  participantLimit;
    String publishedOn;
    Boolean requestModeration;
    State state;
    String title;
    Integer views;

}
