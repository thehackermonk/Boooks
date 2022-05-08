package com.hacker.boooks.bean;

import java.time.LocalDate;

public class ReturnBook {

    LocalDate issueDate;
    LocalDate expectedReturnDate;
    LocalDate returnDate;
    float fine;

    public ReturnBook() {
    }

    public ReturnBook(LocalDate issueDate, LocalDate expectedReturnDate, LocalDate returnDate, float fine) {
        this.issueDate = issueDate;
        this.expectedReturnDate = expectedReturnDate;
        this.returnDate = returnDate;
        this.fine = fine;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
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
