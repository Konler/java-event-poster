package ru.practicum.mainservice.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainservice.enums.StatusRequest;
import ru.practicum.mainservice.model.Request;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Integer> {
    Optional<Request> findRequestByRequester(Integer integer);

    List<Request> findAllByRequester(Integer id);

    Request findByEventAndRequester(Integer eventId, Integer userId);

    List<Request> findAllByEvent(Integer eventId);
    Integer countByEventAndStatus(Integer idEvent, StatusRequest status);
}
