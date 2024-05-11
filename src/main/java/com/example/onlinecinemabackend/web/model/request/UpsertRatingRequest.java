package com.example.onlinecinemabackend.web.model.request;

import com.example.onlinecinemabackend.entity.Film;
import com.example.onlinecinemabackend.entity.Series;
import com.example.onlinecinemabackend.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertRatingRequest {

    private UUID id;

    @NotNull
    private Integer rating;

    @Size(max = 2000, message = "Min comment size is: {min}. Max comment size is: {max}")
    private String comment;

}
