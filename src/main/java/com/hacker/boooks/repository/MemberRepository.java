package com.hacker.boooks.repository;

import com.hacker.boooks.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author [@thehackermonk]
 * @apiNote Repository for 'member' table
 * @since 1.0
 */
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

}
