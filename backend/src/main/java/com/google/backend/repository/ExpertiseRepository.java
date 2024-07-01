package com.google.backend.repository;

import com.google.backend.config.QueryConstant;
import com.google.backend.entity.Expertise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpertiseRepository extends JpaRepository<Expertise, Long> {

    Boolean existsByTitle(String name);

    @Query(value = QueryConstant.EXPERTISE_LIST, nativeQuery = true)
    List<?> getList(Integer limit, Integer offset);
}
