package com.example.onlinecinemabackend.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BriefSeriesResponse {

    private UUID id;

    private String title;

    private String description;

    private Instant start_date;

    private String directorName;

    private Integer genresCount;

    private Integer actorsCount;

    private Integer ratingsCount;

    private Integer seasonsCount;
}

