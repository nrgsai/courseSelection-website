package com.google.backend.service;

import com.google.backend.entity.Unit;
import com.google.backend.enums.RoleEnum;
import com.google.backend.exception.AccessException;
import com.google.backend.mapper.UnitMapper;
import com.google.backend.model.UnitModel;
import com.google.backend.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository repository;
    private final AccessException accessException;

    @Transactional(rollbackFor = Exception.class)
    public UnitModel create(UnitModel model) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        if (repository.existsByLessonIdAndProfessorIdAndDayAndTimeAndIdNot(
                model.getLessonId(),
                model.getProfessorId(),
                model.getDay(),
                model.getTime(),
                0L)
        )
            throw new ServiceException("Unit already exists");
        Unit entity = UnitMapper.get().modelToEntity(model);
        repository.save(entity);
        return UnitMapper.get().entityToModel(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public UnitModel update(UnitModel model) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        if (repository.existsByLessonIdAndProfessorIdAndDayAndTimeAndIdNot(
                model.getLessonId(),
                model.getProfessorId(),
                model.getDay(),
                model.getTime(),
                model.getId())
        )
            throw new ServiceException("Unit already exists");
        Unit entity = UnitMapper.get().modelToEntity(model);
        repository.save(entity);
        return UnitMapper.get().entityToModel(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public UnitModel get(Long id) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        return UnitMapper
                .get()
                .entityToModel(repository.findById(id).orElse(new Unit()));
    }

    @Transactional(readOnly = true)
    public List<?> getList(UnitModel model, Integer limit, Integer offset) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        System.out.println(model);
        return repository.getList(
                limit != null ? limit : 5,
                offset != null ? offset - 1 : 0
        );
    }
}
