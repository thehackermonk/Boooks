package com.hacker.boooks.controller;

import com.hacker.boooks.bean.Member;
import com.hacker.boooks.bean.MemberBO;
import com.hacker.boooks.bean.MemberProfile;
import com.hacker.boooks.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@Tag(name = "Member Management", description = "APIs for managing members")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@SuppressWarnings("unused")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("")
    @Operation(summary = "Get all members", description = "Retrieve a list of all members registered in the library.")
    public ResponseEntity<List<Member>> getMembers() {
        return memberService.getMembers();
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "Get a member by ID", description = "Retrieve the information of a member based on their unique ID.")
    public ResponseEntity<Member> getMember(@PathVariable int memberId) {
        return memberService.getMember(memberId);
    }

    @GetMapping("/{memberId}/profile")
    @Operation(summary = "Get member profile", description = "Retrieve the profile information of a member based on their unique ID.")
    public ResponseEntity<MemberProfile> getMemberProfile(@PathVariable int memberId) {
        return memberService.getMemberProfile(memberId);
    }

    @PostMapping
    @Operation(summary = "Add a member", description = "Add a new member to the library database.")
    public ResponseEntity<String> addMember(@RequestParam String name, @RequestParam String email, @RequestParam String phoneNumber) {
        MemberBO memberBO = new MemberBO(name, email, phoneNumber);
        return memberService.addMember(memberBO);
    }

    @PutMapping("/{memberId}")
    @Operation(summary = "Update a member", description = "Update the information of a member based on their unique ID.")
    public ResponseEntity<String> updateMember(@PathVariable int memberId, @RequestParam String name, @RequestParam String email, @RequestParam String phoneNumber) {
        MemberBO memberBO = new MemberBO(name, email, phoneNumber);
        return memberService.updateMember(memberId, memberBO);
    }

    @DeleteMapping("/{memberId}")
    @Operation(summary = "Delete a member", description = "Delete a member from the library based on their unique ID.")
    public ResponseEntity<String> deleteMember(@PathVariable int memberId) {
        return memberService.deleteMember(memberId);
    }

}
