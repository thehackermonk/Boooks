package com.hacker.boooks.repository;

import com.hacker.boooks.entity.MemberEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository underTest;

    @BeforeEach
    void setUp() {

        MemberEntity member = new MemberEntity(1, "Brandon Reynolds", LocalDate.now(), "brandon.reynolds@gmail.com", "9090974785", "male", "Romance");
        underTest.save(member);

    }

    @Test
    void getLastMemberID() {

        int response = underTest.getLastMemberID();
        assertThat(response).isEqualTo(1);

    }

    @Test
    void getMemberByEmail() {

        MemberEntity response = underTest.getMemberByEmail("brandon.reynolds@gmail.com");
        assertThat(response.getMembershipId()).isEqualTo(1);

    }

    @Test
    void getMemberByContact() {

        MemberEntity response = underTest.getMemberByContact("9090974785");
        assertThat(response.getMembershipId()).isEqualTo(1);

    }

    @Test
    void getMembersOfAgeBetween() {

        List<MemberEntity> response = underTest.getMembersOfAgeBetween(2020, 2025);
        assertThat(response).isNotNull();

    }

    @Test
    void searchMember() {

        List<MemberEntity> response = underTest.searchMember("reynold");
        assertThat(response).isNotNull();

    }
}