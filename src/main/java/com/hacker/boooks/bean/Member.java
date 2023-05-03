package com.hacker.boooks.bean;

import com.hacker.boooks.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Member {

    private int membershipID;
    private String name;
    private LocalDate dateOfBirth;
    private String email;
    private String contact;
    private String favoriteGenre;

    public Member(MemberEntity memberEntity) {
        this.membershipID = memberEntity.getMembershipId();
        this.name = memberEntity.getName();
        this.dateOfBirth = memberEntity.getDateOfBirth().toLocalDate();
        this.email = memberEntity.getEmail();
        this.contact = memberEntity.getContact();
        this.favoriteGenre = memberEntity.getFavoriteGenre();
    }
    
}
