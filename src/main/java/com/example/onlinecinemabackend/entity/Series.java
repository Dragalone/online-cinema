package com.example.onlinecinemabackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Entity(name = "series")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldNameConstants
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    private Instant releaseDate;

    private String description;

    @Column(precision = 9, scale = 2)
    private BigDecimal cost;

    @OneToMany(mappedBy = "series", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Season> seasons = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="series_genre",
            joinColumns= @JoinColumn(name="series_id", referencedColumnName="id"),
            inverseJoinColumns=  @JoinColumn(name="genre_id", referencedColumnName="id"))
    @ToString.Exclude
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="series_actors",
            joinColumns= @JoinColumn(name="series_id", referencedColumnName="id"),
            inverseJoinColumns=  @JoinColumn(name="actor_id", referencedColumnName="id"))
    @ToString.Exclude
    private Set<Actor> actors = new HashSet<>();

    private String previewImageURL;

    @OneToMany(mappedBy = "series", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Rating> ratings = new ArrayList<>();

    public void addRating(Rating rating){

        rating.setSeries(this);
    }

    public void addActor(Actor actor){
        actor.getSeriesList().add(this);
    }

    public void addDirector(Director director){
        director.getSeriesList().add(this);
    }

    public void addSeason(Season season){
        season.setSeries(this);
    }
    public void addGenre(Genre genre){
        genre.getSeriesList().add(this);
    }
}

