package com.google.backend.service;

import com.google.backend.config.CollageConstant;
import com.google.backend.config.ResourceBundle;
import com.google.backend.config.SecurityConfig;
import com.google.backend.config.SecurityUtil;
import com.google.backend.entity.Users;
import com.google.backend.mapper.UsersMapper;
import com.google.backend.model.UserRoleModel;
import com.google.backend.model.UsersModel;
import com.google.backend.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {

    private final UsersRepository repository;

    private RolesService rolesService;
    private UserRoleService userRoleService;

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Autowired
    public void setRolesService(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @Transactional(readOnly = true)
    public Optional<UsersModel> findUsersByUsername(String userName) {
        Optional<Users> users = repository.findUsersByUsername(userName);
        return Optional.ofNullable(UsersMapper.get().entityToModel(users.orElse(null)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> findUser = repository.findUsersByUsername(username);
        if (findUser.isEmpty())
            throw new UsernameNotFoundException(CollageConstant.USER_INVALID);
        return User
                .withUsername(username)
                .password(findUser.get().getPassword())
                .roles(rolesService.getUserRoleNameByUserIdWithoutRole(findUser.get().getId()))
                .accountExpired(!findUser.get().getAccountNonExpired())
                .accountLocked(!findUser.get().getAccountNonLocked())
                .credentialsExpired(!findUser.get().getCredentialsNonExpired())
                .disabled(false)
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    public UsersModel create(UsersModel model) {
        if (this.findUsersByUsername(model.getUsername()).isPresent())
            throw new ServiceException(CollageConstant.USERNAME_CONFLICT);
        encryptedPassword(model);
        Users users = UsersMapper.get().modelToEntity(model);
        checkAndSetDefaultBoolean(users);
        repository.save(users);
        UsersModel createdModel = UsersMapper.get().entityToModel(users);
        createDefaultRoleForUser(createdModel.getId());
        return createdModel;
    }

    private void createDefaultRoleForUser(Long id) {
        if (!userRoleService.existsByUsersId(id)) {
            UserRoleModel model = new UserRoleModel();
            model.setUsersId(id);
            model.setRolesId(CollageConstant.TWO_LONG);
            userRoleService.createUserRole(model);
        }
    }

    private void checkAndSetDefaultBoolean(Users entity) {
        entity.setEnabled(true);
        entity.setAccountNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setCredentialsNonExpired(true);
    }

    private void encryptedPassword(UsersModel usersModel) {
        if (usersModel != null && usersModel.getPassword() != null && !usersModel.getPassword().isEmpty())
            usersModel.setPassword(SecurityConfig.passwordEncoder().encode(usersModel.getPassword()));
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> delete(Long id) {
        Map<String, String> map = Map.of(CollageConstant.STATUS, CollageConstant.SUCCESS);
        if (id.compareTo(CollageConstant.ONE_LONG) == 0)
            throw new ServiceException(ResourceBundle.getMessageByKey("CannotBeDeletedAdminUsers"));
        String token = SecurityUtil.getTokenFromCurrentRequest();
        if (token != null && !token.isEmpty()) {
            Long currentId = SecurityUtil.getCurrentId(token);
            if (id.compareTo(currentId) == 0)
                throw new ServiceException(ResourceBundle.getMessageByKey("CannotBeDeleteCurrentUser"));
        } else
            throw new ServiceException(CollageConstant.INVALID_TOKEN);
        repository.deleteById(id);
        return map;
    }

    @Transactional(readOnly = true)
    public List<?> getListUser(UsersModel model, Integer limit, Integer offset) {
        return repository.getList(
                model.getUsername() != null ? model.getUsername() : "",
                limit != null ? limit : 5,
                offset != null ? offset - 1 : 0
        );
    }

    @Transactional(readOnly = true)
    public String getRole() {
        String token = SecurityUtil.getTokenFromCurrentRequest();
        if (token != null && !token.isEmpty()) {
            Long currentId = SecurityUtil.getCurrentId(token);
            return rolesService.getUserRoleNameByUserIdWithoutRole(currentId);
        }
        throw new ServiceException(CollageConstant.INVALID_TOKEN);
    }
}
