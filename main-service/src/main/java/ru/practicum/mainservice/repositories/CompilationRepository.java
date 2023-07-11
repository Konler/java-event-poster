package ru.practicum.mainservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainservice.model.entities.Compilation;

public interface CompilationRepository extends JpaRepository<Compilation,Integer> {
}