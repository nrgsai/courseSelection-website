package com.google.backend.repository;

import com.google.backend.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Boolean existsByUsersIdAndRolesId(Long userId, Long roleId);

    Boolean existsByUsersId(Long userId);
}
