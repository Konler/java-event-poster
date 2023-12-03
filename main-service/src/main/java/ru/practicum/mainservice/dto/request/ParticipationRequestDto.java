package ru.practicum.mainservice.dto.request;

import lombok.Builder;
import lombok.Data;
import ru.practicum.mainservice.enums.StatusRequest;

@Builder
@Data
public class ParticipationRequestDto {
    private Integer id;
    private String created;
    private Integer event;
    private Integer requester;
    private StatusRequest status;

}
