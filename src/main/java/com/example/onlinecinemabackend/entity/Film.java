package com.example.onlinecinemabackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    private Date release_date;

    @Column(length = 1000)
    private String description;

    private String resource_link;

    @Column(precision = 9, scale = 2)
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="film_genre",
            joinColumns= @JoinColumn(name="film_id", referencedColumnName="id"),
            inverseJoinColumns=  @JoinColumn(name="genre_id", referencedColumnName="id"))
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="film_actors",
            joinColumns= @JoinColumn(name="film_id", referencedColumnName="id"),
            inverseJoinColumns=  @JoinColumn(name="actor_id", referencedColumnName="id"))
    private List<Actor> actors = new ArrayList<>();

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Rating> ratings = new ArrayList<>();
}
