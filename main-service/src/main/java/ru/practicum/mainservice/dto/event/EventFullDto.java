package ru.practicum.mainservice.dto.event;


import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import ru.practicum.mainservice.dto.category.CategoryDto;
import ru.practicum.mainservice.dto.user.UserShortDto;
import ru.practicum.mainservice.enums.StateOfEvent;
import ru.practicum.mainservice.model.Location;

import java.time.LocalDateTime;
@Builder
@Data
public class EventFullDto {
    Integer id;
    String annotation;
    CategoryDto category;
    Integer confirmedRequests;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdOn;
    String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    UserShortDto initiator;
    Location location;
    Boolean paid;
    @Value("0")
    Integer participantLimit;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime publishedOn;
    @Value("true")
    Boolean requestModeration;
    StateOfEvent state;
    String title;
    Integer views;

}
