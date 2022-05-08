package com.hacker.boooks.service;

import com.hacker.boooks.bean.Book;
import com.hacker.boooks.bean.Member;
import com.hacker.boooks.bo.MemberBO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @apiNote Service class for everything related to member
 */
@Service
public interface MemberService {

    /**
     * @apiNote Get member details
     * @author [@thehackermonk]
     * @since 1.0
     */
    Member getMemberDetails(int membershipID);

    /**
     * @apiNote Add new member
     * @author [@thehackermonk]
     * @since 1.0
     */
    Map<String, Boolean> addMember(MemberBO memberBO);

    /**
     * @apiNote To check if contact is unused
     * @author [@thehackermonk]
     * @since 1.0
     */
    Map<String, Boolean> checkContact(String contact);

    /**
     * @apiNote To check if email ID is unused
     * @author [@thehackermonk]
     * @since 1.0
     */
    Map<String, Boolean> checkEmail(String email);

    /**
     * @apiNote Update member
     * @author [@thehackermonk]
     * @since 1.0
     */
    Map<String, Boolean> updateMember(int membershipID, MemberBO memberBO);

    /**
     * @apiNote Remove member
     * @author [@thehackermonk]
     * @since 1.0
     */
    Map<String, Boolean> removeMember(int membershipID);

    /**
     * @apiNote Get books which a member is holding
     * @author [@thehackermonk]
     * @since 1.0
     */
    List<Book> getBooksHoldingByMember(int membershipID);

    /**
     * @apiNote Get membership IDs
     * @author [@thehackermonk]
     * @since 1.0
     */
    List<Integer> getMembershipIDs();

    /**
     * @apiNote Get count of books read genre-wise
     * @author [@thehackermonk]
     * @since 1.0
     */
    Map<String, Integer> getBookCountGenreWise(int membershipID);

}
