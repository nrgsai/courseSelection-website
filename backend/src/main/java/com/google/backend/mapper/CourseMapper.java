package com.google.backend.mapper;

import com.google.backend.entity.Course;
import com.google.backend.model.CourseModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
        , componentModel = "spring"
)
public interface CourseMapper {

    static CourseMapper get() {
        return Mappers.getMapper(CourseMapper.class);
    }

    CourseModel entityToModel(Course entity);

    Course modelToEntity(CourseModel model);
}
