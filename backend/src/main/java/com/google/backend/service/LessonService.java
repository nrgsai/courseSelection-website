package com.google.backend.service;

import com.google.backend.entity.Lesson;
import com.google.backend.enums.RoleEnum;
import com.google.backend.exception.AccessException;
import com.google.backend.mapper.LessonMapper;
import com.google.backend.model.LessonModel;
import com.google.backend.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository repository;
    private final AccessException accessException;

    @Transactional(rollbackFor = Exception.class)
    public LessonModel create(LessonModel model) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        if (repository.existsByNameAndCodeAndIdNot(model.getName(), model.getCode(), 0L))
            throw new ServiceException("Lesson already exists");
        Lesson entity = LessonMapper.get().modelToEntity(model);
        repository.save(entity);
        return LessonMapper.get().entityToModel(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public LessonModel update(LessonModel model) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        if (repository.existsByNameAndCodeAndIdNot(model.getName(), model.getCode(), model.getId()))
            throw new ServiceException("Lesson already exists");
        Lesson entity = LessonMapper.get().modelToEntity(model);
        repository.save(entity);
        return LessonMapper.get().entityToModel(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public LessonModel get(Long id) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        return LessonMapper
                .get()
                .entityToModel(repository.findById(id).orElse(new Lesson()));
    }

    @Transactional(readOnly = true)
    public List<?> getList(LessonModel model, Integer limit, Integer offset) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        System.out.println(model);
        return repository.getList(
                limit != null ? limit : 5,
                offset != null ? offset - 1 : 0
        );
    }
}
