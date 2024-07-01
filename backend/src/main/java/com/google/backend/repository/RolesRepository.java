package com.google.backend.repository;

import com.google.backend.config.QueryConstant;
import com.google.backend.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

    @Query(value = "select r.name from roles r left join users_roles ur on r.id = ur.role_id where ur.user_id = :id", nativeQuery = true)
    String getUserRoleNameByUserId(Long id);

    @Query(value = QueryConstant.GET_ROLE, nativeQuery = true)
    String getRoleByUserId(Long userId);
}
