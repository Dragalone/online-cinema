package com.example.onlinecinemabackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    private Date release_date;

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
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="series_actors",
            joinColumns= @JoinColumn(name="series_id", referencedColumnName="id"),
            inverseJoinColumns=  @JoinColumn(name="actor_id", referencedColumnName="id"))
    private List<Actor> actors = new ArrayList<>();

    @OneToMany(mappedBy = "series", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Rating> ratings = new ArrayList<>();
}
