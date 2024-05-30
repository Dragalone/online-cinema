package com.example.onlinecinemabackend.web.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmFilterRequest {

    @NotNull
    private PaginationRequest pagination;

    private Set<String> genreNames;

    private Set<String> actorNames;

    private String directorName;

}
