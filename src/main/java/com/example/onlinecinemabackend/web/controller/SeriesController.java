package com.example.onlinecinemabackend.web.controller;


import com.example.onlinecinemabackend.entity.Film;
import com.example.onlinecinemabackend.entity.Series;

import com.example.onlinecinemabackend.mapper.SeriesMapper;

import com.example.onlinecinemabackend.service.SeriesService;
import com.example.onlinecinemabackend.web.dto.request.PaginationRequest;
import com.example.onlinecinemabackend.web.dto.request.SeriesFilterRequest;
import com.example.onlinecinemabackend.web.dto.request.UpsertFilmRequest;
import com.example.onlinecinemabackend.web.dto.request.UpsertSeriesRequest;
import com.example.onlinecinemabackend.web.dto.response.FilmResponse;
import com.example.onlinecinemabackend.web.dto.response.ModelListResponse;

import com.example.onlinecinemabackend.web.dto.response.SeriesResponse;
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

    @GetMapping
    public ResponseEntity<ModelListResponse<SeriesResponse>> filterBy(@Valid PaginationRequest pageRequest,
                                                                      @RequestParam String title,
                                                                      @RequestParam Set<String> genres,
                                                                      @RequestParam Set<String> actors,
                                                                      @RequestParam String director) {
        SeriesFilterRequest filter = new SeriesFilterRequest(pageRequest,title,genres,actors,director);
        Page<Series> series = seriesService.filterBy(filter);
        return  ResponseEntity.ok(
                ModelListResponse.<SeriesResponse>builder()
                        .totalCount(series.getTotalElements())
                        .data(series.stream().map(seriesMapper::seriesToResponse).toList())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<SeriesResponse> createSeries(@RequestBody UpsertSeriesRequest request,
                                                   @RequestParam List<UUID> genresIds,
                                                   @RequestParam List<UUID> actorsIds,
                                                   @RequestParam UUID directorId
    ){
        Series series = seriesService.addSeries(seriesMapper.upsertRequestToSeries(request),actorsIds,genresIds,directorId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(seriesMapper.seriesToResponse(series));
    }

}
