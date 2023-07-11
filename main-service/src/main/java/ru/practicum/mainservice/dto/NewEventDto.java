package ru.practicum.mainservice.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import ru.practicum.mainservice.model.Location;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@Builder
@Data
public class NewEventDto {
    @Size(min = 20, max = 2000, message = "Длина краткого описания события должна быть от 20 до 2000")
    String annotation;
    Integer category;
    @Size(min = 20, max = 7000, message = "Длина полного описания события должна быть от 20 до 7000")
    String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    Location location;
    @Value("false")
    private Boolean paid;
    @Value("0")
    Integer participantLimit;
    @Value("true")
    Boolean requestModeration;
    @Size(min = 3, max = 120, message = "")
    String title;
}
