package com.hacker.boooks.repository;

import com.hacker.boooks.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author [@thehackermonk]
 * @apiNote Repository for 'book' table
 * @since 1.0
 */
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    List<BookEntity> findByAuthor(String author);
}
