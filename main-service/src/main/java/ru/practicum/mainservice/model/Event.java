package ru.practicum.mainservice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.mainservice.enums.StateOfEvent;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "events")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id; //----

    @Column(name = "annotation", nullable = false, length = 2000)
     String annotation;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID" )
     Category category;

    @Column(name = "created_on")
     LocalDateTime createdOn; //-----------

    @Column(name = "description")
     String description;

    @Column(name = "event_date", nullable = false)
     LocalDateTime eventDate;

    @ManyToOne
    @JoinColumn(name = "initiator_id")
     User initiator;

    @OneToOne
    @JoinColumn(name = "location")
     Location location;

    @Column(name = "paid")
     Boolean paid;

    @Column(name = "participant_limit")
     Integer participantLimit;

    @Column(name = "published_on")
     LocalDateTime publishedOn;//------------

    @Column(name = "request_moderation")
     Boolean requestModeration;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
     StateOfEvent state ; //------------------

    @Column(name = "title")
     String title;

    @ManyToMany(mappedBy = "events")
    private List<Compilation> compilations;
}
