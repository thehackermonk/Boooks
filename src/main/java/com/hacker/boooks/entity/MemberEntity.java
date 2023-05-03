package com.hacker.boooks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_id")
    int membershipId;
    String name;
    @Column(name = "date_of_birth")
    Date dateOfBirth;
    String email;
    String contact;
    @Column(name = "favorite_genre")
    String favoriteGenre;

}
