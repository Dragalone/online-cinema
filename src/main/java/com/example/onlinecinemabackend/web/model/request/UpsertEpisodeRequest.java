package com.example.onlinecinemabackend.web.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertEpisodeRequest {

    private UUID id;

    private String title;

    private Date release_date;

    private String description;

    private String resource_link;
}
