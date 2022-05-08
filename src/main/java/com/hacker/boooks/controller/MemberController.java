package com.hacker.boooks.controller;

import com.hacker.boooks.bean.Book;
import com.hacker.boooks.bean.Member;
import com.hacker.boooks.bean.MemberProfile;
import com.hacker.boooks.bo.MemberBO;
import com.hacker.boooks.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for everything related to member
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/boooks/member")
@SuppressWarnings("unused")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * @param name          Name of the member
     * @param dateOfBirth   Date of birth of the member
     * @param email         Email ID of the member
     * @param contact       Contact of the member
     * @param gender        Gender of the member
     * @param favoriteGenre Member's favorite genre
     * @return True if addition succeeded, and false otherwise
     * @apiNote To add a new member
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/add")
    @ResponseBody
    public Map<String, Boolean> addMember(@RequestHeader String name, @RequestHeader String dateOfBirth, @RequestHeader String email, @RequestHeader String contact, @RequestHeader String gender, @RequestHeader String favoriteGenre) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        MemberBO member = new MemberBO(name, LocalDate.parse(dateOfBirth, formatter), email, contact, gender, favoriteGenre);

        return memberService.addMember(member);

    }

    /**
     * @param contact Contact of the member
     * @return True if contact is unused, and false otherwise
     * @apiNote To check if contact is unused
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/checkcontact")
    @ResponseBody
    public Map<String, Boolean> checkContact(@RequestHeader String contact) {
        return memberService.checkContact(contact);
    }

    /**
     * @param email Email ID of the member
     * @return True if email ID is unused, and false otherwise
     * @apiNote To check if email ID is unused
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/checkemail")
    @ResponseBody
    public Map<String, Boolean> checkEmail(@RequestHeader String email) {
        return memberService.checkEmail(email);
    }

    /**
     * @param membershipID Unique ID of the member
     * @return True if member is removed, and false otherwise
     * @apiNote To remove a member
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/remove")
    @ResponseBody
    public Map<String, Boolean> removeMember(@RequestHeader int membershipID) {
        return memberService.removeMember(membershipID);
    }

    /**
     * @param membershipID Unique ID of the member
     * @return Books a member is holding
     * @apiNote Get books a member is holding
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/getholdingbooks")
    @ResponseBody
    public List<Book> getBooksMemberHolds(@RequestHeader int membershipID) {
        return memberService.getBooksHoldingByMember(membershipID);
    }

    /**
     * @param membershipID Unique ID of the member
     * @return Count of books read (genre wise)
     * @apiNote Get count of books read (genre wise)
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/getgenresreadbookcount")
    @ResponseBody
    public Map<String, Integer> getBookCountGenreWise(@RequestHeader int membershipID) {
        return memberService.getBookCountGenreWise(membershipID);
    }

    /**
     * @param membershipID Unique ID of the member
     * @return Details of member when membership ID is provided
     * @apiNote Get details of member
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/getdetailsbyid")
    @ResponseBody
    public Member getMemberByID(@RequestHeader int membershipID) {
        return memberService.getMemberDetails(membershipID);
    }

    /**
     * @param membershipID Unique ID of the member
     * @return Member profile
     * @apiNote Get member profile
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/getprofile")
    @ResponseBody
    public MemberProfile getMemberProfile(@RequestHeader int membershipID) {
        return null;
    }

    /**
     * @param membershipID Unique ID of the member
     * @return List of books member is holding
     * @apiNote Get Name of books member is holding
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/getholdingbooknames")
    @ResponseBody
    public List<Book> getBooksHoldingByMember(@RequestHeader int membershipID) {
        return memberService.getBooksHoldingByMember(membershipID);
    }

    /**
     * @return List of member IDs
     * @apiNote Get list of member IDs
     * @author [@thehackermonk]
     * @since 1.0
     */
    @GetMapping("/getmembershipids")
    @ResponseBody
    public List<Integer> getMembershipIDs() {
        return memberService.getMembershipIDs();
    }

    /**
     * @param membershipID Unique ID of the member
     * @return True if member is update succeeded, and false otherwise
     * @apiNote To update a member
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/update")
    @ResponseBody
    public Map<String, Boolean> updateMember(@RequestHeader int membershipID, @RequestHeader String name, @RequestHeader String dateOfBirth, @RequestHeader String email, @RequestHeader String contact, @RequestHeader String gender, @RequestHeader String favoriteGenre) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        MemberBO member = new MemberBO(name, LocalDate.parse(dateOfBirth, formatter), email, contact, gender, favoriteGenre);

        return memberService.updateMember(membershipID, member);

    }

}
