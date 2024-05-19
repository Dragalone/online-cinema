package com.example.onlinecinemabackend.web.model.response;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionResponse {

    private UUID id;

    private Date start_date;

    private Date end_date;

    private BigDecimal cost;
}