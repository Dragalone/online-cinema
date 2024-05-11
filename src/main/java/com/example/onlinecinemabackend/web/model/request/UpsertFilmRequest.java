package com.example.onlinecinemabackend.web.model.request;
import com.example.onlinecinemabackend.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertFilmRequest {

    private UUID id;

    private String title;

    private String description;

    private Date release_date;


    private BigDecimal cost;

    private String resource_link;

}
