package ru.practicum.mainservice.model;

import lombok.*;
import ru.practicum.mainservice.enums.StatusRequest;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
@Table(name = "requests")
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "event_id")
    @JoinColumn(table = "events", name = "id")
    private Integer event;

    @Column(name = "requester_id")
    @JoinColumn(table = "users", name = "id")
    private Integer requester;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusRequest status;
}
