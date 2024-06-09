package com.example.onlinecinemabackend.web.controller;


import com.example.onlinecinemabackend.entity.Film;
import com.example.onlinecinemabackend.entity.Genre;

import com.example.onlinecinemabackend.mapper.GenreMapper;
import com.example.onlinecinemabackend.service.GenreService;
import com.example.onlinecinemabackend.web.dto.request.PaginationRequest;

import com.example.onlinecinemabackend.web.dto.request.UpsertFilmRequest;
import com.example.onlinecinemabackend.web.dto.request.UpsertGenreRequest;
import com.example.onlinecinemabackend.web.dto.response.FilmResponse;
import com.example.onlinecinemabackend.web.dto.response.GenreResponse;
import com.example.onlinecinemabackend.web.dto.response.ModelListResponse;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/genre",produces = "application/json")
public class GenreController {

    private final GenreService genreService;

    private final GenreMapper genreMapper;

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponse> getById(@PathVariable UUID id){
        return  ResponseEntity.ok(
                genreMapper.genreToResponse(genreService.findById(id))
        );
    }


    @GetMapping("/name")
    public ResponseEntity<GenreResponse> getByName(@Valid PaginationRequest request, @RequestParam String name){
        return  ResponseEntity.ok(
                genreMapper.genreToResponse(genreService.findByName(name))
        );
    }
    @GetMapping
    public ResponseEntity<ModelListResponse<GenreResponse>> findAllGenres(@Valid PaginationRequest request){
        Page<Genre> genres = genreService.findAll(request.pageRequest());

        return  ResponseEntity.ok(
                ModelListResponse.<GenreResponse>builder()
                        .totalCount(genres.getTotalElements())
                        .data(genres.stream().map(genreMapper::genreToResponse).toList())
                        .build()
        );
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<GenreResponse> createGenre(@RequestBody UpsertGenreRequest request,
                                                     @Nullable @RequestParam List<UUID> filmsIds,
                                                     @Nullable @RequestParam List<UUID> seriesIds
    ){
        Genre genre = genreService.addGenre(genreMapper.upsertRequestToGenre(request),filmsIds,seriesIds);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(genreMapper.genreToResponse(genre));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<GenreResponse> updateGenre(@RequestBody UpsertGenreRequest request,
                                                     @PathVariable UUID id,
                                                     @RequestParam(required = false) List<UUID> filmsIds,
                                                     @RequestParam(required = false) List<UUID> seriesIds
    ){
        Genre updatedGenre = genreService.updateGenre(genreMapper.upsertRequestToGenre(request),id,filmsIds,seriesIds);
        return ResponseEntity.ok(genreMapper.genreToResponse(updatedGenre));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<Void> deleteGenre(@PathVariable UUID id){
        genreService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
