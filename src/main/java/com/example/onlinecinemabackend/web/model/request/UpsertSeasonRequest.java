package com.example.onlinecinemabackend.web.model.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertSeasonRequest {

    private UUID id;

    @NotNull
    private String title;

    private String description;

    private Instant start_date;

}
