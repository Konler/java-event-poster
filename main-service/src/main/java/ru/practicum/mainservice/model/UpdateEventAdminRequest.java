package ru.practicum.mainservice.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.practicum.mainservice.StateAction;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@Builder
@Data
public class UpdateEventAdminRequest {
    @Size(min = 20, max = 2000, message = "Длина аннотации должна быть в диапазоне от 20 до 2000")
    String annotation;
    Integer category;
    @Size(min = 20, max = 7000, message = "Длина описания должна быть в диапазоне от 20 до 7000")
    String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    Location location;
    Boolean paid;
    Integer participantLimit;
    Boolean requestModeration;
    StateAction stateAction;
    @Size(min = 3, max = 120, message = "Длина заголовка должна быть в диапазоне от 3 до 120")
    String title;
}
