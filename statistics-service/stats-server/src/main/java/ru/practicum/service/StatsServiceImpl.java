package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.EndpointHitDto;
import ru.practicum.ViewStatsDto;
import ru.practicum.exception.BadRequestException;
import ru.practicum.model.EndpointHit;
import ru.practicum.model.EndpointHitMapper;
import ru.practicum.model.ViewStats;
import ru.practicum.model.ViewStatsMapper;
import ru.practicum.repository.EndpointHitsRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final EndpointHitsRepository endpointHitsRepository;
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void addHit(EndpointHitDto endpointHitDto) {
        EndpointHit endpointHit = EndpointHitMapper.fromEndpointHitDto(endpointHitDto);
        endpointHitsRepository.save(endpointHit);
    }

    @Override
    public List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, String[] uris, Boolean unique) {
        if (end.isBefore(start)) {
            log.error("Дата окончания не может быть ранее даты начала");
            throw new BadRequestException("Дата окончания не может быть ранее даты начала");
        }
        List<ViewStats> result;
        if (uris == null) {
            if (unique) {
                result = endpointHitsRepository.getStatsUnique(start, end);
            } else {
                result = endpointHitsRepository.getAll(start, end);
            }
        } else {
            if (unique) {
                result = endpointHitsRepository.getStatsUnique(start, end, uris);
            } else {
                result = endpointHitsRepository.getAll(start, end, uris);
            }
        }
        return result.stream()
                .map(ViewStatsMapper::toViewStatsDto)
                .collect(Collectors.toList());
    }
}