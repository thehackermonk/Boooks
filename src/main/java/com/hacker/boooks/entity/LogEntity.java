package com.hacker.boooks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "log")
public class LogEntity {
    @Id
    @Column(name = "log_id", nullable = false)
    private int logId;
    @Column(name = "book_id", nullable = false)
    private int bookId;
    @Column(name = "member_id", nullable = false)
    private int memberId;
    @Column(name = "issue_date", nullable = false)
    private Date issueDate;
    @Column(name = "return_date")
    private Date returnDate;
    private Float fine;

}