package com.google.backend.exception;

import com.google.backend.config.SecurityUtil;
import com.google.backend.enums.RoleEnum;
import com.google.backend.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccessException {

    private final RolesRepository repository;

    public void validate(String currentRoleAction) {
        String token = SecurityUtil.getTokenFromCurrentRequest();
        if (token == null)
            throw new ServiceException("Invalid token");
        String roleName = repository.getRoleByUserId(SecurityUtil.getCurrentId(token));
        if (roleName == null)
            throw new ServiceException("Invalid role");
        if (roleName.equals(RoleEnum.ROLE_STUDENT.toString())
                && currentRoleAction.equals(RoleEnum.ROLE_ADMIN.toString()))
            throw new ServiceException("You are not allowed to access this resource");
    }
}
