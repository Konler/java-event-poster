package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.model.EndpointHit;
import ru.practicum.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EndpointHitsRepository extends JpaRepository<EndpointHit, Long> {

    @Query("SELECT new ru.practicum.model.ViewStats(h.app, h.uri, COUNT(DISTINCT(h.ip))) " +
            "FROM EndpointHit AS h " +
            "WHERE h.created BETWEEN ?1 AND ?2 AND h.uri IN ?3 " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(DISTINCT(h.ip)) DESC")
    List<ViewStats> getStatsUnique(LocalDateTime start, LocalDateTime end, String[] uris);

    @Query("SELECT new ru.practicum.model.ViewStats(h.app, h.uri, COUNT(DISTINCT(h.ip))) " +
            "FROM EndpointHit AS h " +
            "WHERE h.created BETWEEN ?1 AND ?2 " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(DISTINCT(h.ip)) DESC")
    List<ViewStats> getStatsUnique(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.model.ViewStats(h.app, h.uri, COUNT(h.id)) " +
            "FROM EndpointHit AS h " +
            "WHERE h.created BETWEEN ?1 AND ?2 AND h.uri IN ?3 " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(h.id) DESC")
    List<ViewStats> getAll(LocalDateTime start, LocalDateTime end, String[] uris);

    @Query("SELECT new ru.practicum.model.ViewStats(h.app, h.uri, COUNT(h.id)) " +
            "FROM EndpointHit AS h " +
            "WHERE h.created BETWEEN ?1 AND ?2 " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(h.id) DESC")
    List<ViewStats> getAll(LocalDateTime start, LocalDateTime end);
}