package com.google.backend.service;

import com.google.backend.config.ResourceBundle;
import com.google.backend.entity.UserRoleEntity;
import com.google.backend.mapper.UserRoleMapper;
import com.google.backend.model.UserRoleModel;
import com.google.backend.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public void createUserRole(UserRoleModel model) {
        if (repository.existsByUsersIdAndRolesId(model.getUsersId(), model.getRolesId()))
            throw new ServiceException(ResourceBundle.getMessageByKey("DuplicatedUserRole"));
        UserRoleEntity entity = UserRoleMapper.get().modelToEntity(model);
        repository.save(entity);
    }

    @Transactional(readOnly = true)
    public Boolean existsByUsersId(Long userId) {
        return repository.existsByUsersId(userId);
    }
}
