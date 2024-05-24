package com.example.onlinecinemabackend.mapper;

import com.example.onlinecinemabackend.entity.Subscription;
import com.example.onlinecinemabackend.entity.User;
import com.example.onlinecinemabackend.web.model.request.UpsertSubscriptionRequest;
import com.example.onlinecinemabackend.web.model.request.UpsertUserRequest;
import com.example.onlinecinemabackend.web.model.response.SubscriptionResponse;
import com.example.onlinecinemabackend.web.model.response.UserResponse;
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
