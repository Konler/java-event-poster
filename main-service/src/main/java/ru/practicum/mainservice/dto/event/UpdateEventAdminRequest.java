package ru.practicum.mainservice.dto.event;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.mainservice.enums.StateAction;
import ru.practicum.mainservice.model.Location;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateEventAdminRequest {
    @Size(min = 20, max = 2000, message = "Длина аннотации должна быть в диапазоне от 20 до 2000")
    private String annotation;
    private Integer category;
    @Size(min = 20, max = 7000, message = "Длина описания должна быть в диапазоне от 20 до 7000")
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private StateAction stateAction;
    @Size(min = 3, max = 120, message = "Длина заголовка должна быть в диапазоне от 3 до 120")
    private String title;
}
