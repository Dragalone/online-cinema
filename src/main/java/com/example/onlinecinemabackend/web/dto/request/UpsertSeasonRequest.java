package com.example.onlinecinemabackend.web.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertSeasonRequest {

    private UUID id;

    @NotNull
    private String title;

    private String description;

    private Instant releaseDate;

}
