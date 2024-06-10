package com.example.onlinecinemabackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "director")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldNameConstants
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 127)
    private String name;

    private Instant birthdate;

    @Column(length = 1000)
    private String information;

    private String directorImageURL;

    @OneToMany(mappedBy = "director", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Series> seriesList = new ArrayList<>();

    public void addSeries(Series series) {
        seriesList.add(series);
    }

    @OneToMany(mappedBy = "director", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Film> films = new ArrayList<>();

    public void addFilm(Film film) {
        films.add(film);
    }
}
