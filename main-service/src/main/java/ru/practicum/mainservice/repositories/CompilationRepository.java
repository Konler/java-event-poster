package ru.practicum.mainservice.repositories;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainservice.model.Compilation;

import java.util.List;

public interface CompilationRepository extends JpaRepository<Compilation,Integer> {
    List<Compilation> findAllByPinned(Boolean pinned, Pageable page);
}
