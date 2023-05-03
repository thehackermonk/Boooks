package com.hacker.boooks.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReturnBook {

    LocalDate issueDate;
    LocalDate expectedReturnDate;
    LocalDate returnDate;
    float fine;

}
