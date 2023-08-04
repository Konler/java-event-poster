package ru.practicum.mainservice.dto.event;

import lombok.Builder;
import lombok.Data;
import ru.practicum.mainservice.enums.StatusOfParticipationEvent;

import java.util.List;

@Builder
@Data
public class EventRequestStatusUpdateRequest {
    List<Integer> requestIds;
    /*TODO*/
    StatusOfParticipationEvent status;

}
