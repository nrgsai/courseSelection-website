package com.google.backend.repository;

import com.google.backend.config.QueryConstant;
import com.google.backend.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Boolean existsByNameAndCodeAndIdNot(String name, String code, Long id);

    @Query(value = QueryConstant.LESSON_LIST, nativeQuery = true)
    List<?> getList(Integer limit, Integer offset);
}
