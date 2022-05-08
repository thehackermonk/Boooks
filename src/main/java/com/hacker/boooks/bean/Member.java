package com.hacker.boooks.bean;

import com.hacker.boooks.entity.MemberEntity;

import java.time.LocalDate;

public class Member {

    private int membershipID;
    private String name;
    private LocalDate dateOfBirth;
    private String email;
    private String contact;
    private String gender;
    private String favoriteGenre;

    public Member() {
    }

    public Member(MemberEntity memberEntity) {
        this.membershipID = memberEntity.getMembershipId();
        this.name = memberEntity.getName();
        this.dateOfBirth = memberEntity.getDateOfBirth().toLocalDate();
        this.email = memberEntity.getEmail();
        this.contact = memberEntity.getContact();
        this.gender = memberEntity.getGender();
        this.favoriteGenre = memberEntity.getFavoriteGenre();
    }

    public Member(int membershipID, String name, LocalDate dateOfBirth, String email, String contact, String gender, String favoriteGenre) {
        this.membershipID = membershipID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.contact = contact;
        this.gender = gender;
        this.favoriteGenre = favoriteGenre;
    }

    public int getMembershipID() {
        return membershipID;
    }

    public void setMembershipID(int membershipID) {
        this.membershipID = membershipID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(String favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }
}
