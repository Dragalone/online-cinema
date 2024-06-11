package com.example.onlinecinemabackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Entity(name = "film")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldNameConstants
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 127)
    private String title;

    private Instant releaseDate;

    @Column(length = 5000)
    private String description;

    private String previewImageURL;

    private String resourceLink;

    @Column(precision = 9, scale = 2)
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="film_genre",
            joinColumns= @JoinColumn(name="film_id", referencedColumnName="id"),
            inverseJoinColumns=  @JoinColumn(name="genre_id", referencedColumnName="id"))
    @ToString.Exclude
    private Set<Genre> genres = new HashSet<>();

    public void addGenre(Genre genre){
        genre.getFilms().add(this);
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="film_actors",
            joinColumns= @JoinColumn(name="film_id", referencedColumnName="id"),
            inverseJoinColumns=  @JoinColumn(name="actor_id", referencedColumnName="id"))
    @ToString.Exclude
    private Set<Actor> actors = new HashSet<>();

    public void addActor(Actor actor){
        actor.getFilms().add(this);
    }

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Rating> ratings = new ArrayList<>();



    public void addRating(Rating rating){
        rating.setFilm(this);
    }

    public void addDirector(Director director){
        director.getFilms().add(this);
    }
}
