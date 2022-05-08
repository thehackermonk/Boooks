package com.hacker.boooks.repository;

import com.hacker.boooks.entity.PublisherEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PublisherRepositoryTest {

    @Autowired
    private PublisherRepository underTest;

    @BeforeEach
    void setUp() {

        PublisherEntity publisherEntity = new PublisherEntity(1, "Pearson");
        underTest.save(publisherEntity);

    }

    @Test
    void getLastPublisherID() {

        int response = underTest.getLastPublisherID();
        assertThat(response).isEqualTo(1);

    }

    @Test
    void getPublisher() {

        PublisherEntity response = underTest.getPublisher("Pearson");
        assertThat(response.getPublisherId()).isEqualTo(1);

    }

    @Test
    void searchPublisher() {

        List<PublisherEntity> response = underTest.searchPublisher("pearson");
        assertThat(response).isNotNull();

    }
}