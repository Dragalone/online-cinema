package com.example.onlinecinemabackend.web.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertRatingRequest {

    @NotNull
    private Integer rating;

    @Size(max = 2000, message = "Min comment size is: {min}. Max comment size is: {max}")
    private String comment;

}
