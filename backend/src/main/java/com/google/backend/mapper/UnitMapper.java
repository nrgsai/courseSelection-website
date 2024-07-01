package com.google.backend.mapper;

import com.google.backend.entity.Unit;
import com.google.backend.model.UnitModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
        , componentModel = "spring"
)
public interface UnitMapper {

    static UnitMapper get() {
        return Mappers.getMapper(UnitMapper.class);
    }

    UnitModel entityToModel(Unit entity);

    Unit modelToEntity(UnitModel model);
}
