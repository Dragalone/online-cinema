package com.example.onlinecinemabackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Entity(name = "subscription")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldNameConstants
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Instant start_date;

    @Column(nullable = false)
    private Instant end_date;

    @Column(precision = 9, scale = 2)
    private BigDecimal cost;

}
