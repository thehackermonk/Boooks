package com.hacker.boooks.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "log")
public class LogEntity {

    @Id
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

    public LogEntity() {
    }

    public LogEntity(int slNo, int bookId, int membershipId, LocalDate issueDate, LocalDate returnDate, float fine) {
        this.slNo = slNo;
        this.bookId = bookId;
        this.membershipId = membershipId;
        this.issueDate = Date.valueOf(issueDate);
        this.returnDate = Date.valueOf(returnDate);
        this.fine = fine;
    }

    public int getSlNo() {
        return slNo;
    }

    public void setSlNo(int slNo) {
        this.slNo = slNo;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public float getFine() {
        return fine;
    }

    public void setFine(float fine) {
        this.fine = fine;
    }
}
