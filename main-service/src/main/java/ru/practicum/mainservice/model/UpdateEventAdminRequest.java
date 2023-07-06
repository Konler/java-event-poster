package ru.practicum.mainservice.model;


import ru.practicum.mainservice.StateAction;

public class UpdateEventAdminRequest {
    String annotation;
    Integer category;
    String description;
    String eventDate;
    Location location;
    Boolean paid;
    Integer participantLimit;
    Boolean requestModeration;
    StateAction stateAction;
    String title;

}
