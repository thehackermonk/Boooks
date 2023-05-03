package com.hacker.boooks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "log")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sl_no")
    int slNo;
    @Column(name = "book_id")
    int bookId;
    @Column(name = "membership_id")
    int membershipId;
    @Column(name = "issue_date")
    Date issueDate;
    @Column(name = "return_date")
    Date returnDate;
    float fine;

}
