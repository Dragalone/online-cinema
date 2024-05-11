package com.example.onlinecinemabackend.web.model.request;

import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.entity.Season;
import jakarta.validation.constraints.NotNull;
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
public class UpsertSeriesRequest {

    private UUID id;

    @NotNull
    private String title;

    private Date release_date;

    private String description;

    private BigDecimal cost;

}
