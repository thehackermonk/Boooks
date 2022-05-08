package com.hacker.boooks.repository;

import com.hacker.boooks.entity.LogEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class LogRepositoryTest {

    @Autowired
    private LogRepository underTest;

    @BeforeEach
    void setUp() {

        long now = System.currentTimeMillis();

        LogEntity logEntity1 = new LogEntity(1, 1, 1, LocalDate.now(), LocalDate.now(), 12.5f);
        underTest.save(logEntity1);
        LogEntity logEntity2 = new LogEntity(2, 2, 1, LocalDate.now(), null, 0);
        underTest.save(logEntity2);

    }

    @Test
    void getLastSlNo() {

        int response = underTest.getLastSlNo();
        assertThat(response).isEqualTo(2);

    }

    @Test
    void getUnreturnedBookLog() {

        LogEntity response = underTest.getUnreturnedBookLog(2, 1);
        assertThat(response.getSlNo()).isEqualTo(2);

    }

    @Test
    void getUnreturnedBooks() {

        List<LogEntity> response = underTest.getUnreturnedBooks(1);
        assertThat(response).isNotNull();

    }

    @Test
    void whoHoldsTheBook() {

        int response = underTest.whoHoldsTheBook(2);
        assertThat(response).isEqualTo(1);

    }

    @Test
    void getBookCount() {

        int response = underTest.getBookCount(2);
        assertThat(response).isEqualTo(1);

    }

    @Test
    void getBooksReadBy() {

        List<Integer> response = underTest.getBooksReadBy(1);
        assertThat(response).isNotNull();

    }
}