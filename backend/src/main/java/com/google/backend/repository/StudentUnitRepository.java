package com.google.backend.repository;

import com.google.backend.config.QueryConstant;
import com.google.backend.entity.StudentUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentUnitRepository extends JpaRepository<StudentUnit, Long> {

    Boolean existsByUsersIdAndUnitId(Long usersId, Long unitId);

    @Query(value = QueryConstant.STUDENT_UNIT_LIST, nativeQuery = true)
    List<?> getList(Integer limit, Integer offset);
}
