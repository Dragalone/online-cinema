package com.example.onlinecinemabackend.web.dto.response;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectorResponse {

    private UUID id;

    private String name;

    private Instant birthdate;

    private String information;

}
