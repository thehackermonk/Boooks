package com.hacker.boooks.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fine")
public class FineEntity {

    @Id
    @Column(name = "sl_no")
    int slNo;
    @Column(name = "days_for_fine")
    int daysForFine;
    float amount;

    public FineEntity() {
    }

    public FineEntity(int slNo, int daysForFine, float amount) {
        this.slNo = slNo;
        this.daysForFine = daysForFine;
        this.amount = amount;
    }

    public int getSlNo() {
        return slNo;
    }

    public void setSlNo(int slNo) {
        this.slNo = slNo;
    }

    public int getDaysForFine() {
        return daysForFine;
    }

    public void setDaysForFine(int daysForFine) {
        this.daysForFine = daysForFine;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
