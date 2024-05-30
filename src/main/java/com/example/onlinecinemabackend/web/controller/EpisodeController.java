package com.example.onlinecinemabackend.web.controller;

import com.example.onlinecinemabackend.entity.Episode;

import com.example.onlinecinemabackend.mapper.EpisodeMapper;
import com.example.onlinecinemabackend.service.EpisodeService;
import com.example.onlinecinemabackend.web.model.request.PaginationRequest;
import com.example.onlinecinemabackend.web.model.response.EpisodeResponse;

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
@RequestMapping(path = "/api/v1/episode",produces = "application/json")
public class EpisodeController {

    private final EpisodeService episodeService;

    private final EpisodeMapper episodeMapper;

    @GetMapping("/{id}")
    public ResponseEntity<EpisodeResponse> getById(@PathVariable UUID id){
        return  ResponseEntity.ok(
                episodeMapper.episodeToResponse(episodeService.findById(id))
        );
    }
    @GetMapping("/title/all")
    public ResponseEntity<ModelListResponse<EpisodeResponse>> getAllByTitle(@Valid PaginationRequest request, @RequestParam String title){
        Page<Episode> episodes = episodeService.findAllByTitle(title, request.pageRequest());

        return  ResponseEntity.ok(
                ModelListResponse.<EpisodeResponse>builder()
                        .totalCount(episodes.getTotalElements())
                        .data(episodes.stream().map(episodeMapper::episodeToResponse).toList())
                        .build()
        );
    }
}
