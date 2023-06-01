package com.hacker.boooks.repository;

import com.hacker.boooks.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<AuthEntity, String> {
}