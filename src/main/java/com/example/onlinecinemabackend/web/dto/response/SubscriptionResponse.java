package com.example.onlinecinemabackend.web.dto.response;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionResponse {

    private UUID id;

    private Instant start_date;

    private Instant end_date;

    private BigDecimal cost;
}
