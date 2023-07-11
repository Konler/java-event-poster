package ru.practicum.mainservice.model;

import lombok.Builder;
import lombok.Data;
import ru.practicum.mainservice.StatusOfParticipationEvent;

import java.util.List;
@Builder
@Data
public class EventRequestStatusUpdateRequest {
    List<Integer> requestIds;
    /*TODO*/
    StatusOfParticipationEvent status;

}
