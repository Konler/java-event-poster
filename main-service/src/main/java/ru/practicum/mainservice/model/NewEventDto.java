package ru.practicum.mainservice.model;

import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class NewEventDto {
    @Size(min = 20,max = 2000, message = "Длина краткого описания события должна быть от 20 до 2000")
   String  annotation;
   Integer category;
   @Size(min = 20,max=7000,message = "Длина полного описания события должна быть от 20 до 7000")
   String description;
   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   LocalDateTime eventDate;
   Location location;
   
   Boolean paid;
    Integer participantLimit;
    Boolean requestModeration;
    String title;
}
