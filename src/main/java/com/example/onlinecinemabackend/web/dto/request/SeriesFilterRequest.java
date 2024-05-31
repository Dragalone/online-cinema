package com.example.onlinecinemabackend.web.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeriesFilterRequest {


    @NotNull
    private PaginationRequest pagination;

    private String title;

    private Set<String> genreNames;

    private Set<String> actorNames;

    private String directorName;

}
