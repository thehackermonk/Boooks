package com.hacker.boooks.entity;

import com.hacker.boooks.bo.MemberBO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    @Column(name = "membership_id")
    int membershipId;
    String name;
    @Column(name = "date_of_birth")
    Date dateOfBirth;
    String email;
    String contact;
    String gender;
    @Column(name = "favorite_genre")
    String favoriteGenre;

    public MemberEntity() {
    }

    public MemberEntity(int membershipId, MemberBO memberBO) {
        this.membershipId = membershipId;
        this.name = memberBO.getName();
        this.dateOfBirth = java.sql.Date.valueOf(memberBO.getDateOfBirth());
        this.email = memberBO.getEmail();
        this.contact = memberBO.getContact();
        this.gender = memberBO.getGender();
        this.favoriteGenre = memberBO.getFavoriteGenre();
    }

    public MemberEntity(int membershipId, String name, LocalDate dateOfBirth, String email, String contact, String gender, String favoriteGenre) {
        this.membershipId = membershipId;
        this.name = name;
        this.dateOfBirth = java.sql.Date.valueOf(dateOfBirth);
        this.email = email;
        this.contact = contact;
        this.gender = gender;
        this.favoriteGenre = favoriteGenre;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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
