package ru.practicum.mainservice.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
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
    private String annotation;

    private Integer category;

    @Size(min = 20, max = 7000, message = "Длина полного описания события должна быть от 20 до 7000")
    @NotBlank
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private Location location;


    private Boolean paid = false;

    private Integer participantLimit = 0;

    private Boolean requestModeration = true;

    @Size(min = 3, max = 120, message = "Длина заголовка ")
    private String title;
}
