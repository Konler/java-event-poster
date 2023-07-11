package ru.practicum.mainservice.model;

import lombok.Builder;
import lombok.Data;
import ru.practicum.mainservice.dto.ParticipationRequestDto;
@Data
@Builder
public class EventRequestStatusUpdateResult {
    ParticipationRequestDto confirmedRequests;
    ParticipationRequestDto rejectedRequests;
}
