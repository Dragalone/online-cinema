package com.example.onlinecinemabackend.web.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertActorRequest {

    private UUID id;

    @NotNull
    private String name;

    private Instant birthdate;

    private String information;
}
