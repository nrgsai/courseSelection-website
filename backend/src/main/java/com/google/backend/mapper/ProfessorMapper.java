package com.google.backend.mapper;

import com.google.backend.entity.Professor;
import com.google.backend.model.ProfessorModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
        , componentModel = "spring"
)
public interface ProfessorMapper {

    static ProfessorMapper get() {
        return Mappers.getMapper(ProfessorMapper.class);
    }

    ProfessorModel entityToModel(Professor entity);

    Professor modelToEntity(ProfessorModel model);
}
