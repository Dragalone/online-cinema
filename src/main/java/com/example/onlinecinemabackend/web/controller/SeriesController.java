package com.example.onlinecinemabackend.web.controller;


import com.example.onlinecinemabackend.entity.Film;
import com.example.onlinecinemabackend.entity.Series;

import com.example.onlinecinemabackend.mapper.SeriesMapper;

import com.example.onlinecinemabackend.service.SeriesService;
import com.example.onlinecinemabackend.web.model.request.PaginationRequest;
import com.example.onlinecinemabackend.web.model.response.FilmResponse;
import com.example.onlinecinemabackend.web.model.response.ModelListResponse;

import com.example.onlinecinemabackend.web.model.response.SeriesResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/series",produces = "application/json")
public class SeriesController {
    private final SeriesService seriesService;

    private final SeriesMapper seriesMapper;

    @GetMapping("/{id}")
    public ResponseEntity<SeriesResponse> getById(@PathVariable UUID id){
        return  ResponseEntity.ok(
                seriesMapper.seriesToResponse(seriesService.findById(id))
        );
    }
    @GetMapping("/title/all")
    public ResponseEntity<ModelListResponse<SeriesResponse>> getAllByTitle(@Valid PaginationRequest request, @RequestParam String title){
        Page<Series> series = seriesService.findAllByTitle(title, request.pageRequest());

        return  ResponseEntity.ok(
                ModelListResponse.<SeriesResponse>builder()
                        .totalCount(series.getTotalElements())
                        .data(series.stream().map(seriesMapper::seriesToResponse).toList())
                        .build()
        );
    }
    @GetMapping
    public ResponseEntity<ModelListResponse<SeriesResponse>> findAllFilms(@Valid PaginationRequest request){
        Page<Series> series = seriesService.findAll(request.pageRequest());
        return  ResponseEntity.ok(
                ModelListResponse.<SeriesResponse>builder()
                        .totalCount(series.getTotalElements())
                        .data(series.stream().map(seriesMapper::seriesToResponse).toList())
                        .build()
        );
    }
    @GetMapping("/title")
    public ResponseEntity<SeriesResponse> getByTitle(@RequestParam String title){
        return  ResponseEntity.ok(
                seriesMapper.seriesToResponse(seriesService.findByTitle(title))
        );
    }


}
