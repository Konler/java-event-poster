package ru.practicum.mainservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainservice.model.entities.Request;

public interface RequestRepository extends JpaRepository<Request,Integer> {
}
