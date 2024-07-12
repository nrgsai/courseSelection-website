package com.google.backend.service;

import com.google.backend.entity.StudentUnit;
import com.google.backend.enums.RoleEnum;
import com.google.backend.exception.AccessException;
import com.google.backend.mapper.StudentUnitMapper;
import com.google.backend.model.StudentUnitModel;
import com.google.backend.repository.StudentUnitRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentUnitService {

    private final StudentUnitRepository repository;
    private final AccessException accessException;

    @Transactional(rollbackFor = Exception.class)
    public StudentUnitModel create(StudentUnitModel model) {
        accessException.validate(RoleEnum.ROLE_STUDENT.toString());
        if (repository.existsByUsersIdAndUnitId(model.getUsersId(), model.getUnitId()))
            throw new ServiceException("this unit was selected by student");
        StudentUnit entity = StudentUnitMapper.get().modelToEntity(model);
        repository.save(entity);
        return StudentUnitMapper.get().entityToModel(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        accessException.validate(RoleEnum.ROLE_STUDENT.toString());
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<?> getList(StudentUnitModel model, Integer limit, Integer offset) {
        accessException.validate(RoleEnum.ROLE_STUDENT.toString());
        System.out.println(model);
        return repository.getList(
                limit != null ? limit : 5,
                offset != null ? offset - 1 : 0
        );
    }
}
