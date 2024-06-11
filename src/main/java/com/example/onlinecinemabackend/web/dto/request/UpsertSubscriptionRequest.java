package com.example.onlinecinemabackend.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertSubscriptionRequest {

    private Instant startDate;

    private Instant endDate;

    private BigDecimal cost;
}
