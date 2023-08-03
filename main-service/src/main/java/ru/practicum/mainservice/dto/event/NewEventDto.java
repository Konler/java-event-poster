package ru.practicum.mainservice.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import ru.practicum.mainservice.model.Location;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewEventDto {
    @NotBlank
    @Size(min = 20, max = 2000, message = "Длина краткого описания события должна быть от 20 до 2000")
    String annotation;

    Integer category;

    @Size(min = 20, max = 7000, message = "Длина полного описания события должна быть от 20 до 7000")
    @NotBlank
    String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;

    Location location;

     @Value("false")

    private Boolean paid=false;

    @Value("0")

    Integer participantLimit=0;

    @Value("true")

    Boolean requestModeration=true;

    @Size(min = 3, max = 120, message = "Длина заголовка ")
    String title;
}
