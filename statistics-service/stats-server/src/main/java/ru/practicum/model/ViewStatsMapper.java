package ru.practicum.model;

import lombok.experimental.UtilityClass;
import ru.practicum.dto.ViewStatsDto;

@UtilityClass
public class ViewStatsMapper {

    public static ViewStatsDto toViewStatsDto(ViewStats viewStats) {
        return ViewStatsDto.builder()
                .app(viewStats.getApp())
                .uri(viewStats.getUri())
                .hits(viewStats.getHits())
                .build();
    }
}