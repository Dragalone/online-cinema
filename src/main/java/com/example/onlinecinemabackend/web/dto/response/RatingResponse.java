package com.example.onlinecinemabackend.web.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponse {

    private UUID id;

    private Integer rating;

    private String comment;

}
