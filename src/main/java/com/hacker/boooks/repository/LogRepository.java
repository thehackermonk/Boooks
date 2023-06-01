package com.hacker.boooks.repository;

import com.hacker.boooks.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LogRepository extends JpaRepository<LogEntity, Integer> {
    Optional<LogEntity> findByMemberIdAndReturnDateIsNull(int memberId);

    List<LogEntity> findByBookIdIn(List<Integer> collect);
}
