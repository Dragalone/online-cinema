package com.example.onlinecinemabackend.web.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertSeriesRequest {

    @NotNull
    private String title;

    private Instant releaseDate;

    private String description;

    private BigDecimal cost;

}
