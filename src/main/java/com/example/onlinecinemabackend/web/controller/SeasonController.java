package com.example.onlinecinemabackend.web.controller;


import com.example.onlinecinemabackend.entity.Season;

import com.example.onlinecinemabackend.mapper.SeasonMapper;

import com.example.onlinecinemabackend.service.SeasonService;
import com.example.onlinecinemabackend.web.dto.request.PaginationRequest;

import com.example.onlinecinemabackend.web.dto.response.EpisodeResponse;
import com.example.onlinecinemabackend.web.dto.response.ModelListResponse;
import com.example.onlinecinemabackend.web.dto.response.SeasonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/season",produces = "application/json")
public class SeasonController {

    private final SeasonService seasonService;

    private final SeasonMapper seasonMapper;

    @GetMapping("/{id}")
    public ResponseEntity<SeasonResponse> getById(@PathVariable UUID id){
        return  ResponseEntity.ok(
                seasonMapper.seasonToResponse(seasonService.findById(id))
        );
    }
    @GetMapping("/title/all")
    public ResponseEntity<ModelListResponse<SeasonResponse>> getAllByTitle(@Valid PaginationRequest request, @RequestParam String title){
        Page<Season> seasons = seasonService.findAllByTitle(title, request.pageRequest());
        return  ResponseEntity.ok(
                ModelListResponse.<SeasonResponse>builder()
                        .totalCount(seasons.getTotalElements())
                        .data(seasons.stream().map(seasonMapper::seasonToResponse).toList())
                        .build()
        );
    }
    @GetMapping("/title")
    public ResponseEntity<SeasonResponse> getByTitle(@RequestParam String title){
        return  ResponseEntity.ok(
                seasonMapper.seasonToResponse(seasonService.findByTitle(title))
        );
    }
}
