package com.example.onlinecinemabackend.web.controller;


import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.mapper.DirectorMapper;
import com.example.onlinecinemabackend.service.DirectorService;
import com.example.onlinecinemabackend.web.model.request.PaginationRequest;

import com.example.onlinecinemabackend.web.model.response.DirectorResponse;

import com.example.onlinecinemabackend.web.model.response.ModelListResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/director")
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

}
