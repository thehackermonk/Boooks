package com.hacker.boooks.repository;

import com.hacker.boooks.entity.LoginEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class LoginRepositoryTest {

    @Autowired
    private LoginRepository underTest;

    @BeforeEach
    void setUp() {

        long now = System.currentTimeMillis();
        Timestamp sqlTimestamp = new Timestamp(now);

        LoginEntity loginEntity = new LoginEntity("admin", "Admin@123", "1234567890", sqlTimestamp);
        underTest.save(loginEntity);

    }

    @Test
    void getLoginByUsername() {

        LoginEntity response = underTest.getLoginByUsername("admin");
        assertThat(response.getPassword()).isEqualTo("Admin@123");

    }

    @Test
    void getLoginByUsernameAndToken() {

        LoginEntity response = underTest.getLoginByUsernameAndToken("admin", "1234567890");
        assertThat(response.getPassword()).isEqualTo("Admin@123");

    }
}