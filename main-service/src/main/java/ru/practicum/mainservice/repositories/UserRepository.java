package ru.practicum.mainservice.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainservice.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
//    @Query("select  u from User u where u.id in ?1")
//    List<User> findAllById(List<Integer> ids, PageRequest page);

    List<User> findByIdIn(List<Integer> ids, PageRequest page);
}
