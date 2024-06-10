package com.example.onlinecinemabackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.*;

@Entity(name = "genre")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldNameConstants
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length =   127)
    private String name;

    @ManyToMany
    @JoinTable(name="series_genre",
            joinColumns=  @JoinColumn(name="genre_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="series_id", referencedColumnName="id"))
    @ToString.Exclude
    private Set<Series> seriesList = new HashSet<>();

    public void addSeries(Series series){
        seriesList.add(series);
    }


    @ManyToMany
    @JoinTable(name="film_genre",
            joinColumns=  @JoinColumn(name="genre_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="film_id", referencedColumnName="id"))
    @ToString.Exclude
    private Set<Film> films = new HashSet<>();

    public void addFilm(Film film){
        films.add(film);
    }
}
