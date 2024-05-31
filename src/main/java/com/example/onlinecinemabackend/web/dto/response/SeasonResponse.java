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
public class SeasonResponse {

    private UUID id;

    private String title;

    private String description;

    private Instant start_date;

    private List<EpisodeResponse> episodes = new ArrayList<>();
}
