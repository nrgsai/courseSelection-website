package com.google.backend.repository;

import com.google.backend.config.QueryConstant;
import com.google.backend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findUsersByUsername(String userName);

    @Query(value = QueryConstant.USER_LIST_VALUE, nativeQuery = true)
    List<?> getList(String username, Integer limit, Integer offset);
}
