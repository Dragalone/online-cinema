package com.example.onlinecinemabackend.service.impl;

import com.example.onlinecinemabackend.aop.AccessCheckType;
import com.example.onlinecinemabackend.service.AbstractAccessCheckerService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserAccessCheckerService extends AbstractAccessCheckerService {

    @Override
    public AccessCheckType getType() {
        return AccessCheckType.USER;
    }

    @Override
    protected boolean check(UUID entityId, UUID userId) {
        return entityId.equals(userId);
    }
}
