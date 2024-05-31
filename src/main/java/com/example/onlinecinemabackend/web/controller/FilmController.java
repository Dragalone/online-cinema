package com.example.onlinecinemabackend.web.controller;


import com.example.onlinecinemabackend.entity.Film;

import com.example.onlinecinemabackend.mapper.FilmMapper;

import com.example.onlinecinemabackend.service.FilmService;
import com.example.onlinecinemabackend.web.model.request.FilmFilterRequest;
import com.example.onlinecinemabackend.web.model.request.PaginationRequest;
import com.example.onlinecinemabackend.web.model.response.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/film",produces = "application/json")
public class FilmController {

    private final FilmMapper filmMapper;

    private final FilmService filmService;



    @GetMapping("/{id}")
    public ResponseEntity<FilmResponse> getById(@PathVariable UUID id){
        return  ResponseEntity.ok(
                filmMapper.filmToResponse(filmService.findById(id))
        );
    }
    @GetMapping("/filter")
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
    @GetMapping
    public ResponseEntity<ModelListResponse<FilmResponse>> findAllFilms(@Valid PaginationRequest request){
        Page<Film> films = filmService.findAll(request.pageRequest());
        return  ResponseEntity.ok(
                ModelListResponse.<FilmResponse>builder()
                        .totalCount(films.getTotalElements())
                        .data(films.stream().map(filmMapper::filmToResponse).toList())
                        .build()
        );
    }


    @GetMapping("/title/all")
    public ResponseEntity<ModelListResponse<FilmResponse>> getAllByTitle(@Valid PaginationRequest request, @RequestParam String title){
        Page<Film> films = filmService.findAllByTitle(title, request.pageRequest());

        return  ResponseEntity.ok(
                ModelListResponse.<FilmResponse>builder()
                        .totalCount(films.getTotalElements())
                        .data(films.stream().map(filmMapper::filmToResponse).toList())
                        .build()
        );
    }

    @GetMapping("/title")
    public ResponseEntity<FilmResponse> getByTitle(@RequestParam String title){

        return  ResponseEntity.ok(
               filmMapper.filmToResponse(filmService.findByTitle(title))
        );
    }




}
