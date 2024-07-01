package com.google.backend.mapper;

import com.google.backend.entity.StudentUnit;
import com.google.backend.model.StudentUnitModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
        , componentModel = "spring"
)
public interface StudentUnitMapper {

    static StudentUnitMapper get() {
        return Mappers.getMapper(StudentUnitMapper.class);
    }

    @Mapping(target = "users", ignore = true)
    @Mapping(target = "unit", ignore = true)
    StudentUnitModel entityToModel(StudentUnit entity);

    StudentUnit modelToEntity(StudentUnitModel model);
}
