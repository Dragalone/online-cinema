package com.example.onlinecinemabackend.service;

import com.example.onlinecinemabackend.aop.Accessible;
import com.example.onlinecinemabackend.entity.RoleType;
import com.example.onlinecinemabackend.security.AppUserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractAccessCheckerService implements AccessCheckerService {

    private static final String ID = "id";

    @Override
    @SuppressWarnings("unchecked")
    public boolean check(HttpServletRequest request, Accessible accessible) {
        if (!isUserAuthenticated()) {
            return false;
        }
        if (hasAccessByRole(accessible)) {
            return true;
        }

        var pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        UUID id = UUID.fromString(pathVariables.get(ID));
        var user = (AppUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID userId = user.getId();

        return check(id, userId);
    }

    protected abstract boolean check(UUID entityId, UUID userId);

    protected boolean isUserAuthenticated() {
        return SecurityContextHolder.getContext() != null &&
                SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AppUserPrincipal;
    }

    protected boolean hasAccessByRole(Accessible accessible) {
        if (!isUserAuthenticated()) {
            return false;
        }

        var user = (AppUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return accessible.availableForModerator() &&
                user.hasAnyRole(List.of(RoleType.ROLE_ADMIN, RoleType.ROLE_MODERATOR));
    }

}
