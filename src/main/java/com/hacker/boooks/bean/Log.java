package com.hacker.boooks.bean;

import com.hacker.boooks.entity.LogEntity;

import java.time.LocalDate;

public class Log {

    private Book book;
    private Member member;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private float fine;

    public Log() {
    }

    public Log(Book book, Member member, LocalDate issueDate, LocalDate returnDate, float fine) {
        this.book = book;
        this.member = member;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.fine = fine;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public float getFine() {
        return fine;
    }

    public void setFine(float fine) {
        this.fine = fine;
    }
}
