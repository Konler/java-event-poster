package ru.practicum.mainservice.comment.model;

import lombok.*;
import ru.practicum.mainservice.comment.CommentStatus;
import ru.practicum.mainservice.model.Event;
import ru.practicum.mainservice.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
@Builder(toBuilder = true)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "text", nullable = false, length = 7000)
    private String text;
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    @Column(name = "created")
    private LocalDateTime created;
    @Enumerated(EnumType.STRING)
    private CommentStatus status;
}
