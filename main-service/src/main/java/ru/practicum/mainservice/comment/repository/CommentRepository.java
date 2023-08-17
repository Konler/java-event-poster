package ru.practicum.mainservice.comment.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainservice.comment.model.Comment;

import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Optional<Comment> findCommentById(Integer commentId);

    Boolean existsByIdAndAuthorId(Integer commentId, Integer userId);

    Page<Comment> findAllByAuthorId(Integer authorId, PageRequest pageable);

    Optional<Comment> findByIdAndAuthorId(Integer commentId, Integer userId);

    Page<Comment> findAllByEventId(Integer eventId, PageRequest pageable);
}
