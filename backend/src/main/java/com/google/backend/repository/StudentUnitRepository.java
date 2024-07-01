package com.google.backend.repository;

import com.google.backend.entity.StudentUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentUnitRepository extends JpaRepository<StudentUnit, Long> {

}
