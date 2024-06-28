package com.google.backend.service;

import com.google.backend.config.CollageConstant;
import com.google.backend.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
public class RolesService implements Serializable {

    private final RolesRepository repository;

    @Transactional(readOnly = true)
    public String getUserRoleNameByUserIdWithoutRole(Long id) {
        try {
            String name = repository.getUserRoleNameByUserId(id);
            if (name == null || name.isEmpty())
                throw new Exception("UserRoleNotFound");
            name = name.substring(CollageConstant.DEFAULT_SECURITY_ROLE_CONSTANT.length());
            return name;
        } catch (Exception e) {
            e.fillInStackTrace();
            return HttpStatus.NOT_FOUND.name();
        }
    }
}
