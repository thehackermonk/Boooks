package com.hacker.boooks.bo;

import java.time.LocalDate;

/**
 * @author [@thehackermonk]
 * @apiNote To read member details
 * @since 1.0
 */
public class MemberBO {

    private String name;
    private LocalDate dateOfBirth;
    private String email;
    private String contact;
    private String gender;
    private String favoriteGenre;

    public MemberBO() {
    }

    public MemberBO(String name, LocalDate dateOfBirth, String email, String contact, String gender, String favoriteGenre) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.contact = contact;
        this.gender = gender;
        this.favoriteGenre = favoriteGenre;
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
