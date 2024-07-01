package com.google.backend.mapper;

import com.google.backend.entity.Expertise;
import com.google.backend.model.ExpertiseModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
        , componentModel = "spring"
)
public interface ExpertiseMapper {

    static ExpertiseMapper get() {
        return Mappers.getMapper(ExpertiseMapper.class);
    }

    ExpertiseModel entityToModel(Expertise entity);

    Expertise modelToEntity(ExpertiseModel model);
}
