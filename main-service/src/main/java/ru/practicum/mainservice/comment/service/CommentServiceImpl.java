package ru.practicum.mainservice.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.mainservice.comment.CommentStatus;
import ru.practicum.mainservice.comment.dto.CommentDto;
import ru.practicum.mainservice.comment.dto.NewCommentDto;
import ru.practicum.mainservice.comment.mapper.CommentMapper;
import ru.practicum.mainservice.comment.model.Comment;
import ru.practicum.mainservice.comment.repository.CommentRepository;
import ru.practicum.mainservice.exceptions.ConflictException;
import ru.practicum.mainservice.exceptions.NotFoundException;
import ru.practicum.mainservice.model.Event;
import ru.practicum.mainservice.model.User;
import ru.practicum.mainservice.repositories.EventRepository;
import ru.practicum.mainservice.repositories.UserRepository;
import ru.practicum.mainservice.util.General;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public CommentDto addComment(NewCommentDto newCommentDto, Integer userId, Integer eventId) {
        User user = validateUser(userId);
        Event event = validateEvent(eventId);
        Comment comment = CommentMapper.toComment(newCommentDto);
        comment.setAuthor(user);
        comment.setEvent(event);
        comment.setStatus(CommentStatus.PENDING);
        CommentDto savedComment = CommentMapper.toCommentDto(commentRepository.save(comment));
        log.info("Комментарий {} сохранен", savedComment);
        return savedComment;
    }

    @Override
    public CommentDto renewalComment(NewCommentDto newCommentDto, Integer userId, Integer commentId) {
        Comment comment = validateComment(commentId);
        validateUser(userId);
        if (!comment.getAuthor().getId().equals(userId)) {
            log.error("Только автор или администратор может обновить комментарий");
            throw new ConflictException("Только автор или администратор может обновить комментарий");
        }
        comment.setText(newCommentDto.getText());
        comment.setStatus(CommentStatus.PENDING);
        CommentDto updatedComment = CommentMapper.toCommentDto(commentRepository.save(comment));
        log.info("Комментарий с id {} обновлен", commentId);
        return updatedComment;
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDto getCommentById(Integer userId, Integer commentId) {
        log.info("Получение собственного комментария с id {}", commentId);
        validateUser(userId);
        Comment comment = validateComment(commentId);
        return CommentMapper.toCommentDto(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getAllUserComments(Integer userId, Integer from, Integer size) {
        log.info("Получение списка всех комментарией пользователя с id {}", userId);
        validateUser(userId);
        PageRequest pageable = General.toPage(from, size);
        List<Comment> comments = commentRepository.findAllByAuthorId(userId, pageable).getContent();
        return comments.stream()
                .map(CommentMapper::toCommentDto)
                .sorted(Comparator.comparing(CommentDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCommentById(Integer userId, Integer commentId) {
        Comment comment = validateComment(commentId);
        if (!comment.getAuthor().getId().equals(userId)) {
            log.error("Только автор или администратор может улалить комментарий");
            throw new ConflictException("Только автор или администратор может обновить комментарий");
        }
        commentRepository.deleteById(commentId);
        log.info("Комментарий с id {} удален", commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getAllCommentsForEvent(Integer eventId, Integer from, Integer size) {
        log.info("Получение списка комментариев о событии с id {}", eventId);
        validateEvent(eventId);
        PageRequest pageable = General.toPage(from, size);
        List<Comment> comments = commentRepository.findAllByEventId(eventId, pageable).getContent();
        return comments.stream()
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto renewalCommentAdmin(Integer commentId, NewCommentDto newCommentDto) {
        Comment comment = validateComment(commentId);
        comment.setText(newCommentDto.getText());
        comment.setStatus(CommentStatus.PUBLISHED);
        CommentDto updatedComment = CommentMapper.toCommentDto(commentRepository.save(comment));
        log.info("Комментарий с id {} обновлен администратором", commentId);
        return updatedComment;
    }

    @Override
    public void deleteCommentAdmin(Integer commentId) {
        validateComment(commentId);
        commentRepository.deleteById(commentId);
        log.info("Комментарий с id {} удален", commentId);
    }

    private User validateUser(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException(
                "Пользователь с id " + userId + " не найден"));
    }

    private Event validateEvent(Integer eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(
                "Событие с id " + eventId + " не найдено"));
    }

    private Comment validateComment(Integer commentId) {
        return commentRepository.findCommentById(commentId).orElseThrow(() -> new NotFoundException(
                "Комментарий с id " + commentId + " не найден"));
    }
}
