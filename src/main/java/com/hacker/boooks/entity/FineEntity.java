package com.hacker.boooks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fine")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sl_no")
    int slNo;
    @Column(name = "days_overdue")
    int daysOverdue;
    @Column(name = "fine_amount")
    float fineAmount;

}
