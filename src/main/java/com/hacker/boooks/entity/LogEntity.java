package com.hacker.boooks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "log")
public class LogEntity {
    @Id
    @Column(name = "log_id", nullable = false)
    private Integer logId;
    @Column(name = "book_id", nullable = false)
    private Integer bookId;
    @Column(name = "member_id", nullable = false)
    private Integer memberId;
    @Column(name = "issue_date", nullable = false)
    private Date issueDate;
    @Column(name = "return_date")
    private Date return_date;
    private Float fine;

}