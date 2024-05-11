package com.example.onlinecinemabackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity(name = "actor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldNameConstants
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 127)
    private String name;

    private Date birthdate;

    @Column(length = 1000)
    private String information;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="series_actors",
            joinColumns=  @JoinColumn(name="actor_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="series_id", referencedColumnName="id"))
    private List<Series> series = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="film_actors",
            joinColumns=  @JoinColumn(name="actor_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="film_id", referencedColumnName="id"))
    private List<Series> films = new ArrayList<>();
}
