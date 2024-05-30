package com.example.onlinecinemabackend.web.controller;


import com.example.onlinecinemabackend.entity.Genre;

import com.example.onlinecinemabackend.mapper.GenreMapper;
import com.example.onlinecinemabackend.service.GenreService;
import com.example.onlinecinemabackend.web.model.request.PaginationRequest;

import com.example.onlinecinemabackend.web.model.response.GenreResponse;
import com.example.onlinecinemabackend.web.model.response.ModelListResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
