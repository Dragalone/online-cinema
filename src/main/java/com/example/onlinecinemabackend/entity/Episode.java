package com.example.onlinecinemabackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Entity(name = "episode")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldNameConstants
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 127)
    private String title;

    private Instant releaseDate;

    @Column(length = 1000)
    private String description;

    private String resourceLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    @ToString.Exclude
    private Season season;
}
