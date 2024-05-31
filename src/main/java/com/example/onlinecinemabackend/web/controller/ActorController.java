package com.example.onlinecinemabackend.web.controller;

import com.example.onlinecinemabackend.entity.Actor;
import com.example.onlinecinemabackend.mapper.ActorMapper;
import com.example.onlinecinemabackend.service.ActorService;
import com.example.onlinecinemabackend.web.dto.request.PaginationRequest;
import com.example.onlinecinemabackend.web.dto.response.ActorResponse;
import com.example.onlinecinemabackend.web.dto.response.ModelListResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/actor",produces = "application/json")
public class ActorController {

    private final ActorMapper actorMapper;

    private final ActorService actorService;

    @GetMapping("/{id}")
    public ResponseEntity<ActorResponse> getById(@PathVariable UUID id){
        return  ResponseEntity.ok(
                actorMapper.actorToResponse(actorService.findById(id))
        );
    }
    @GetMapping("/name/all")
    public ResponseEntity<ModelListResponse<ActorResponse>> getAllByName(@Valid PaginationRequest request, @RequestParam String name){
        Page<Actor> actors = actorService.findAllByName(name, request.pageRequest());

        return  ResponseEntity.ok(
                ModelListResponse.<ActorResponse>builder()
                        .totalCount(actors.getTotalElements())
                        .data(actors.stream().map(actorMapper::actorToResponse).toList())
                        .build()
        );
    }
    @GetMapping("/name")
    public ResponseEntity<ActorResponse> getByName(@RequestParam String name){
        return  ResponseEntity.ok(
                actorMapper.actorToResponse(actorService.findByName(name))
        );
    }

    @GetMapping
    public ResponseEntity<ModelListResponse<ActorResponse>> findAllActors(@Valid PaginationRequest request){
            Page<Actor> actors = actorService.findAll(request.pageRequest());
            return  ResponseEntity.ok(
                    ModelListResponse.<ActorResponse>builder()
                            .totalCount(actors.getTotalElements())
                            .data(actors.stream().map(actorMapper::actorToResponse).toList())
                            .build()
            );
    }



}
