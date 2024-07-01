package com.google.backend.mapper;

import com.google.backend.entity.Lesson;
import com.google.backend.model.LessonModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
        , componentModel = "spring"
)
public interface LessonMapper {

    static LessonMapper get() {
        return Mappers.getMapper(LessonMapper.class);
    }

    LessonModel entityToModel(Lesson entity);

    Lesson modelToEntity(LessonModel model);
}
