package com.example.onlinecinemabackend.mapper;

import com.example.onlinecinemabackend.entity.User;
import com.example.onlinecinemabackend.web.model.request.UpsertUserRequest;
import com.example.onlinecinemabackend.web.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {
    User upsertRequestToUser(UpsertUserRequest request);

    UserResponse userToResponse(User user);
}
