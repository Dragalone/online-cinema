package com.example.onlinecinemabackend.web.controller;

import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.entity.Episode;

import com.example.onlinecinemabackend.mapper.EpisodeMapper;
import com.example.onlinecinemabackend.service.EpisodeService;
import com.example.onlinecinemabackend.web.dto.request.PaginationRequest;
import com.example.onlinecinemabackend.web.dto.request.UpsertDirectorRequest;
import com.example.onlinecinemabackend.web.dto.request.UpsertEpisodeRequest;
import com.example.onlinecinemabackend.web.dto.response.DirectorResponse;
import com.example.onlinecinemabackend.web.dto.response.EpisodeResponse;

import com.example.onlinecinemabackend.web.dto.response.ModelListResponse;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/title")
    public ResponseEntity<EpisodeResponse> getByTitle(@RequestParam String title){
        return  ResponseEntity.ok(
                episodeMapper.episodeToResponse(episodeService.findByTitle(title))
        );
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<EpisodeResponse> createEpisode(@RequestBody UpsertEpisodeRequest request,
                                                         @RequestParam UUID seasonId
    ){
        Episode episode = episodeService.addEpisode(episodeMapper.upsertRequestToEpisode(request),seasonId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(episodeMapper.episodeToResponse(episode));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<EpisodeResponse> updateEpisode(@RequestBody UpsertEpisodeRequest request,
                                                         @PathVariable UUID id,
                                                         @RequestParam UUID seasonId
    ){
        Episode updatedEpisode = episodeService.updateEpisode(episodeMapper.upsertRequestToEpisode(request),id,seasonId);
        return ResponseEntity.ok(episodeMapper.episodeToResponse(updatedEpisode));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<Void> deleteEpisode(@PathVariable UUID id){
        episodeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
