package com.google.backend.service;

import com.google.backend.entity.Course;
import com.google.backend.enums.RoleEnum;
import com.google.backend.exception.AccessException;
import com.google.backend.mapper.CourseMapper;
import com.google.backend.model.CourseModel;
import com.google.backend.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;
    private final AccessException accessException;

    @Transactional(rollbackFor = Exception.class)
    public CourseModel create(CourseModel model) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        Course entity = CourseMapper.get().modelToEntity(model);
        repository.save(entity);
        return CourseMapper.get().entityToModel(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public CourseModel get(Long id) {
        accessException.validate(RoleEnum.ROLE_STUDENT.toString());
        return CourseMapper
                .get()
                .entityToModel(repository.findById(id).orElse(new Course()));
    }

    @Transactional(readOnly = true)
    public List<?> getList() {
        accessException.validate(RoleEnum.ROLE_STUDENT.toString());
        return repository.findAllBy();
    }
}
