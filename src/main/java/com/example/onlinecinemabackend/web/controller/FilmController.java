package com.example.onlinecinemabackend.web.controller;


import com.example.onlinecinemabackend.entity.Episode;
import com.example.onlinecinemabackend.entity.Film;

import com.example.onlinecinemabackend.mapper.FilmMapper;

import com.example.onlinecinemabackend.service.FilmService;
import com.example.onlinecinemabackend.service.RatingService;
import com.example.onlinecinemabackend.web.dto.request.FilmFilterRequest;
import com.example.onlinecinemabackend.web.dto.request.PaginationRequest;
import com.example.onlinecinemabackend.web.dto.request.UpsertEpisodeRequest;
import com.example.onlinecinemabackend.web.dto.request.UpsertFilmRequest;
import com.example.onlinecinemabackend.web.dto.response.*;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/film",produces = "application/json")
public class FilmController {

    private final FilmMapper filmMapper;

    private final FilmService filmService;

    private final RatingService ratingService;

    @GetMapping("/{id}")
    public ResponseEntity<FilmResponse> getById(@PathVariable UUID id){
        return  ResponseEntity.ok(
                filmMapper.filmToResponse(filmService.findById(id))
        );
    }

    @GetMapping
    public ResponseEntity<ModelListResponse<FilmResponse>> filterBy(@Valid PaginationRequest pageRequest,
                                                                    @RequestParam String title,
                                                                    @RequestParam Set<String> genres,
                                                                    @RequestParam Set<String> actors,
                                                                    @RequestParam String director) {
        FilmFilterRequest filter = new FilmFilterRequest(pageRequest,title,genres,actors,director);
        Page<Film> films = filmService.filterBy(filter);
        return  ResponseEntity.ok(
                ModelListResponse.<FilmResponse>builder()
                        .totalCount(films.getTotalElements())
                        .data(films.stream().map(filmMapper::filmToResponse).toList())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<FilmResponse> createFilm(@RequestBody UpsertFilmRequest request,
                                                   @RequestParam List<UUID> genresIds,
                                                   @RequestParam List<UUID> actorsIds,
                                                   @RequestParam UUID directorId
    ){
        Film film = filmService.addFilm(filmMapper.upsertRequestToFilm(request),genresIds,actorsIds,directorId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(filmMapper.filmToResponse(film));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmResponse> updateFilm(@RequestBody UpsertFilmRequest request,
                                                   @PathVariable UUID id,
                                                   @RequestParam List<UUID> genresIds,
                                                   @RequestParam List<UUID> actorsIds,
                                                   @RequestParam UUID directorId
    ){
        Film updatedFilm = filmService.updateFilm(filmMapper.upsertRequestToFilm(request),id,genresIds,actorsIds,directorId);
        return ResponseEntity.ok(filmMapper.filmToResponse(updatedFilm));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable UUID id){
        filmService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
