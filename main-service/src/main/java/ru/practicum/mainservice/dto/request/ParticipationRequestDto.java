package ru.practicum.mainservice.dto.request;

import lombok.Builder;
import lombok.Data;
import ru.practicum.mainservice.enums.StatusRequest;

@Builder
@Data
public class ParticipationRequestDto {
    Integer id;

    String created;
    Integer event;
    Integer requester;
    StatusRequest status;

}
