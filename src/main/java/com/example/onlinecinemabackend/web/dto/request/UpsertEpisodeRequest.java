package com.example.onlinecinemabackend.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertEpisodeRequest {

    private String title;

    private Instant releaseDate;

    private String description;

    private String resourceLink;

    private String previewImageURL;

}
