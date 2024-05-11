package com.example.onlinecinemabackend.web.model.response;

import com.example.onlinecinemabackend.entity.Season;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EpisodeResponse {

    private UUID id;

    private String title;

    private Date release_date;

    private String description;

    private String resource_link;

}
