package com.google.backend.service;

import com.google.backend.entity.Expertise;
import com.google.backend.enums.RoleEnum;
import com.google.backend.exception.AccessException;
import com.google.backend.mapper.ExpertiseMapper;
import com.google.backend.model.ExpertiseModel;
import com.google.backend.repository.ExpertiseRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertiseService {

    private final ExpertiseRepository repository;
    private final AccessException accessException;

    @Transactional(rollbackFor = Exception.class)
    public ExpertiseModel create(ExpertiseModel model) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        if (repository.existsByTitle(model.getTitle()))
            throw new ServiceException("Expertise already exists");
        Expertise entity = ExpertiseMapper.get().modelToEntity(model);
        repository.save(entity);
        return ExpertiseMapper.get().entityToModel(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ExpertiseModel get(Long id) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        return ExpertiseMapper
                .get()
                .entityToModel(repository.findById(id).orElse(new Expertise()));
    }

    @Transactional(readOnly = true)
    public List<?> getList(ExpertiseModel model, Integer limit, Integer offset) {
        accessException.validate(RoleEnum.ROLE_ADMIN.toString());
        System.out.println(model);
        return repository.getList(
                limit != null ? limit : 5,
                offset != null ? offset - 1 : 0
        );
    }
}
