package ru.practicum.mainservice.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainservice.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
