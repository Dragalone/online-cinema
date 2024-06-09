package com.example.onlinecinemabackend.web.controller;


import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.mapper.DirectorMapper;
import com.example.onlinecinemabackend.service.DirectorService;
import com.example.onlinecinemabackend.web.dto.request.PaginationRequest;

import com.example.onlinecinemabackend.web.dto.request.UpsertDirectorRequest;
import com.example.onlinecinemabackend.web.dto.response.DirectorResponse;

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
@RequestMapping(path = "/api/v1/director",produces = "application/json")
public class DirectorController {

    private final DirectorService directorService;

    private final DirectorMapper directorMapper;

    @GetMapping("/{id}")
    public ResponseEntity<DirectorResponse> getById(@PathVariable UUID id){
        return  ResponseEntity.ok(
                directorMapper.directorToResponse(directorService.findById(id))
        );
    }
    @GetMapping("/name")
    public ResponseEntity<DirectorResponse> getByName(@RequestParam String name){

        return  ResponseEntity.ok(
                directorMapper.directorToResponse(directorService.findByName(name))
        );
    }

    @GetMapping("/name/all")
    public ResponseEntity<ModelListResponse<DirectorResponse>> findAllByName(@Valid PaginationRequest request, @RequestParam String name){

        Page<Director> directors = directorService.findAllByName(name,request.pageRequest());

        return  ResponseEntity.ok(
                ModelListResponse.<DirectorResponse>builder()
                        .totalCount(directors.getTotalElements())
                        .data(directors.stream().map(directorMapper::directorToResponse).toList())
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ModelListResponse<DirectorResponse>> findAllDirectors(@Valid PaginationRequest request){

        Page<Director> directors = directorService.findAll(request.pageRequest());

        return  ResponseEntity.ok(
                ModelListResponse.<DirectorResponse>builder()
                        .totalCount(directors.getTotalElements())
                        .data(directors.stream().map(directorMapper::directorToResponse).toList())
                        .build()
        );
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<DirectorResponse> createDirector(@RequestBody UpsertDirectorRequest request,
                                                           @Nullable @RequestParam List<UUID> filmsIds,
                                                           @Nullable @RequestParam List<UUID> seriesIds
    ){
           Director director = directorService.addDirector(directorMapper.upsertRequestToDirector(request), filmsIds, seriesIds);
           return ResponseEntity.status(HttpStatus.CREATED)
                .body(directorMapper.directorToResponse(director));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<DirectorResponse> updateDirector(@RequestBody UpsertDirectorRequest request,
                                                           @PathVariable UUID id,
                                                           @RequestParam(required = false) List<UUID> filmsIds,
                                                           @RequestParam(required = false) List<UUID> seriesIds
    ){
        Director updatedDirector = directorService.updateDirector(directorMapper.upsertRequestToDirector(request),id, filmsIds, seriesIds);
        return ResponseEntity.ok(directorMapper.directorToResponse(updatedDirector));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public ResponseEntity<Void> deleteDirector(@PathVariable UUID id){
        directorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
