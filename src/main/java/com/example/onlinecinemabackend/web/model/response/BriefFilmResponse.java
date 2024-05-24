package com.example.onlinecinemabackend.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BriefFilmResponse {

    private UUID id;

    private String title;

    private String description;

    private Date releaseDate;

    private BigDecimal cost;

    private String resourceLink;

    private String directorName;

    private Integer genresCount;

    private Integer actorsCount;

    private Integer ratingsCount;
}
