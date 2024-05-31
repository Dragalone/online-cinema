package com.example.onlinecinemabackend.mapper;

import com.example.onlinecinemabackend.entity.Subscription;
import com.example.onlinecinemabackend.web.dto.request.UpsertSubscriptionRequest;
import com.example.onlinecinemabackend.web.dto.response.SubscriptionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SubscriptionMapper {
    Subscription upsertRequestToSubscription(UpsertSubscriptionRequest request);

    SubscriptionResponse subscriptionToResponse(Subscription subscription);

}
