package com.example.onlinecinemabackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "season")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldNameConstants
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 127)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private Instant releaseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    @ToString.Exclude
    private Series series;

    private String previewImageURL;

    @OneToMany(mappedBy = "season", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Episode> episodes = new ArrayList<>();

    public void addEpisode(Episode episode){
        episode.setSeason(this);
    }
}
