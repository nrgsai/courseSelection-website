package com.google.backend.repository;

import com.google.backend.config.QueryConstant;
import com.google.backend.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Boolean existsByNationalCodeAndIdNot(String nationalCode, Long id);

    @Query(value = QueryConstant.PROFESSOR_LIST, nativeQuery = true)
    List<?> getList(Integer limit, Integer offset);
}
