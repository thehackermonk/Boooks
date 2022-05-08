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

    /**
     * @return Return the last membership_id or return 0 if empty
     * @apiNote Get the last membership_id
     * @since 1.0
     */
    @Query(value = "SELECT IFNULL(MAX(membership_id),0) FROM member", nativeQuery = true)
    int getLastMemberID();

    /**
     * @param email Email ID of the member
     * @return Member details
     * @apiNote Get member details while email is provided
     * @since 1.0
     */
    @Query(value = "SELECT * FROM member WHERE email=?1", nativeQuery = true)
    MemberEntity getMemberByEmail(String email);

    /**
     * @param contact Contact of the member
     * @return Member details
     * @apiNote Get member details while contact is provided
     * @since 1.0
     */
    @Query(value = "SELECT * FROM member WHERE contact=?1", nativeQuery = true)
    MemberEntity getMemberByContact(String contact);

    /**
     * @param startYear start year
     * @param endYear   end year
     * @return Members who were born between start year and end year
     * @apiNote Get members who were born between start year and end year
     * @since 1.0
     */
    @Query(value = "SELECT * FROM `member` WHERE YEAR(`date_of_birth`) IN (?1,?2)", nativeQuery = true)
    List<MemberEntity> getMembersOfAgeBetween(int startYear, int endYear);

    /**
     * @param keyword search keyword
     * @return Members who have the keyword in their name
     * @apiNote Get members who have the keyword in their name
     * @since 1.0
     */
    @Query(value = "SELECT * FROM `member` WHERE LOWER(`name`) LIKE LOWER(?1)", nativeQuery = true)
    List<MemberEntity> searchMember(String keyword);

}
