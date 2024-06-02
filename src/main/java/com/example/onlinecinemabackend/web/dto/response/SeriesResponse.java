package com.example.onlinecinemabackend.web.dto.response;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeriesResponse {

    private UUID id;

    private String title;

    private String description;

    private Instant start_date;

    private DirectorResponse director;

    private List<GenreResponse> genres = new ArrayList<>();

    private List<ActorResponse> actors = new ArrayList<>();

    private List<RatingResponse> ratings = new ArrayList<>();

    private List<SeasonResponse> seasons = new ArrayList<>();

    private Double averageRating;
}
