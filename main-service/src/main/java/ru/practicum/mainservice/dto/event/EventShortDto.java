package ru.practicum.mainservice.dto.event;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.practicum.mainservice.dto.category.CategoryDto;
import ru.practicum.mainservice.dto.user.UserShortDto;

import java.time.LocalDateTime;
@Builder
@Data
public class EventShortDto {
    Integer id;
    String annotation;
    CategoryDto category;
    Integer confirmedRequests;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    UserShortDto initiator;
    Boolean paid;
    String title;
    Integer views;


}
