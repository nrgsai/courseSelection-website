package com.google.backend.mapper;

import com.google.backend.entity.UserRoleEntity;
import com.google.backend.model.UserRoleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
        , componentModel = "spring"
)
public interface UserRoleMapper {

    static UserRoleMapper get() {
        return Mappers.getMapper(UserRoleMapper.class);
    }

    @Mapping(target = "users", ignore = true)
    @Mapping(target = "roles", ignore = true)
    UserRoleEntity modelToEntity(UserRoleModel model);
}
