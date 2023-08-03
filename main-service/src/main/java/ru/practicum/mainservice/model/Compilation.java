package ru.practicum.mainservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "compilations")
public class Compilation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    //  @Column(name = "events",unique = true)

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compilation")
    List<Event> events;

    @Column(name = "pinned", nullable = false)
    Boolean pinned;

    @Column(name = "title")
    String title;
}
