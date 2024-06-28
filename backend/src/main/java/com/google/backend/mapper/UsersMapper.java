package com.google.backend.mapper;

import com.google.backend.config.BaseMapperConfig;
import com.google.backend.entity.Users;
import com.google.backend.model.UsersModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
        , componentModel = "spring"
)
public interface UsersMapper extends BaseMapperConfig<Users, UsersModel> {

    static UsersMapper get() {
        return Mappers.getMapper(UsersMapper.class);
    }

    UsersModel entityToModel(Users entity);

    Users modelToEntity(UsersModel model);
}
