package com.google.backend.repository;

import com.google.backend.config.QueryConstant;
import com.google.backend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = QueryConstant.COURSE_LIST, nativeQuery = true)
    List<?> getList(String name, String instructor);

    List<?> findAllBy();
}
