package com.google.backend.service;

import com.google.backend.entity.Professor;
import com.google.backend.enums.RoleEnum;
import com.google.backend.exception.AccessException;
import com.google.backend.mapper.ProfessorMapper;
import com.google.backend.model.ProfessorModel;
import com.google.backend.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository repository;
    private final AccessException accessException;

    @Transactional(rollbackFor = Exception.class)
    public ProfessorModel create(ProfessorModel model) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        if (repository.existsByNationalCodeAndIdNot(model.getNationalCode(), 0L))
            throw new ServiceException("Professor already exists");
        Professor entity = ProfessorMapper.get().modelToEntity(model);
        repository.save(entity);
        return ProfessorMapper.get().entityToModel(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public ProfessorModel update(ProfessorModel model) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        if (repository.existsByNationalCodeAndIdNot(model.getNationalCode(), model.getId()))
            throw new ServiceException("Professor already exists");
        Professor entity = ProfessorMapper.get().modelToEntity(model);
        repository.save(entity);
        return ProfessorMapper.get().entityToModel(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ProfessorModel get(Long id) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        return ProfessorMapper
                .get()
                .entityToModel(repository.findById(id).orElse(new Professor()));
    }

    @Transactional(readOnly = true)
    public List<?> getList(ProfessorModel model, Integer limit, Integer offset) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        System.out.println(model);
        return repository.getList(
                limit != null ? limit : 5,
                offset != null ? offset - 1 : 0
        );
    }
}
