package com.example.onlinecinemabackend.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EpisodeResponse {

    private UUID id;

    private String title;

    private Instant release_date;

    private String description;

    private String resource_link;

}
