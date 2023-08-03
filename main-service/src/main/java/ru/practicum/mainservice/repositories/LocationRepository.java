package ru.practicum.mainservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainservice.model.Location;

public interface LocationRepository extends JpaRepository<Location,Integer> {
}
