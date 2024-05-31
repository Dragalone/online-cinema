package com.example.onlinecinemabackend.web.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertFilmRequest {

    private UUID id;

    private String title;

    private String description;

    private Instant release_date;

    private BigDecimal cost;

    private String resource_link;

}
