package com.example.onlinecinemabackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="series_genre",
            joinColumns=  @JoinColumn(name="genre_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="series_id", referencedColumnName="id"))
    private List<Series> series = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="film_genre",
            joinColumns=  @JoinColumn(name="genre_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="film_id", referencedColumnName="id"))
    private List<Film> films = new ArrayList<>();
}
