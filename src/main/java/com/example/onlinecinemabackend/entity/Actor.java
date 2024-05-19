package com.example.onlinecinemabackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.*;


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
    @ToString.Exclude
    private Set<Series> seriesList = new HashSet<>();

    public void addSeries(Series series) {
        seriesList.add(series);
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="film_actors",
            joinColumns=  @JoinColumn(name="actor_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="film_id", referencedColumnName="id"))
    @ToString.Exclude
    private Set<Film> films = new HashSet<>();

    public void addFilm(Film film) {
        films.add(film);
    }
}
