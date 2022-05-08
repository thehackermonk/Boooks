package com.hacker.boooks.repository;

import com.hacker.boooks.entity.FineEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class FineRepositoryTest {

    @Autowired
    private FineRepository underTest;

    @BeforeEach
    void setUp() {

        FineEntity fineEntity = new FineEntity(1, 5, 10);
        underTest.save(fineEntity);

    }

    @Test
    void getDaysAfterFineIsCharged() {

        int response = underTest.getDaysAfterFineIsCharged();
        assertThat(response).isEqualTo(5);

    }

    @Test
    void getFineAmount() {

        float response = underTest.getFineAmount();
        assertThat(response).isEqualTo(10);

    }
}