package com.google.backend.repository;

import com.google.backend.config.QueryConstant;
import com.google.backend.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    Boolean existsByLessonIdAndProfessorIdAndDayAndTimeAndIdNot(Long lessonId, Long professorId, Integer day, Integer time, Long id);

    @Query(value = QueryConstant.UNIT_LIST, nativeQuery = true)
    List<?> getList(Integer limit, Integer offset);
}
