package com.google.backend.service;

import com.google.backend.entity.Course;
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

    @Transactional(rollbackFor = Exception.class)
    public CourseModel create(CourseModel model) {
        Course entity = CourseMapper.get().modelToEntity(model);
        repository.save(entity);
        return CourseMapper.get().entityToModel(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public CourseModel get(Long id) {
        return CourseMapper
                .get()
                .entityToModel(repository.findById(id).orElse(new Course()));
    }

    @Transactional(readOnly = true)
    public List<?> getList() {
        return repository.findAllBy();
    }
}
