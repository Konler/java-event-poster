package ru.practicum.mainservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainservice.model.entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {
}
